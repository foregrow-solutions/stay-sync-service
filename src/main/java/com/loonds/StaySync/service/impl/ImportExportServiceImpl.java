package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.model.dto.ChannelDto;
import com.loonds.StaySync.model.dto.UserDto;
import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.model.entity.Guest;
import com.loonds.StaySync.model.entity.User;
import com.loonds.StaySync.repository.ChannelRepository;
import com.loonds.StaySync.repository.GuestRepository;
import com.loonds.StaySync.repository.UserRepository;
import com.loonds.StaySync.util.CsvUtil;
import com.loonds.acl.service.ImportExportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportExportServiceImpl implements ImportExportService {
    private final ChannelRepository channelRepository;
    private final GuestRepository guestRepository;

    private final UserRepository userRepository;

    public void importFromCsv(MultipartFile file) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter('\t');
        List<String[]> records = CsvUtil.importFromCsv(file, csvFormat);

        // Start importing data from the second row (index 1)
        for (int i = 1; i < records.size(); i++) {
            String[] record = records.get(i);

            log.info("Processing record: {}", Arrays.toString(record));

            // Convert the record to JSON format
            UserDto userJson = convertToUserDto(record);
//            ChannelDto channelJson = convertToChannelJson(record);

            // Save the JSON data to the respective database tables
            importUserData(userJson);
//            importChannelData(channelJson);
        }
    }

    private UserDto convertToUserDto(String[] record) {
        if (record == null || record.length < 4) {
            // Handle the case when the record is null or does not have enough elements
            // You can log an error or throw an exception as needed
            // For example:
            throw new IllegalArgumentException("Invalid record format for UserDto.");
        }else {
            log.info("Record is here : {}", record[0]);
        }

        UserDto userDto = new UserDto();
        userDto.setId(record[0]);
        userDto.setFirstName(record[1]);
        userDto.setLastName(record[2]);
//        userDto.setEmail(record[3]);
        // Set other properties accordingly
        return userDto;
    }

    private ChannelDto convertToChannelJson(String[] record) {
        ChannelDto channelJson = new ChannelDto();
        channelJson.setId(record[0]);
        // Set other properties accordingly
        return channelJson;
    }

    private void importUserData(UserDto userJson) {
        User user = new User();
        user.setId(userJson.getId());
        user.setFirstName(userJson.getFirstName());
        user.setLastName(userJson.getLastName());
//        user.setEmail(userJson.getEmail());
        // Set other properties accordingly
        userRepository.save(user);
    }

    private void importChannelData(ChannelDto channelJson) {
        Channel channel = new Channel();
        channel.setId(channelJson.getId());
        // Set other properties accordingly
        channelRepository.save(channel);
    }

    @Override
    public byte[] exportToCsv() throws IOException {
        List<Class<?>> entities = Arrays.asList(Channel.class, Guest.class); // Add more entity classes here

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

        for (Class<?> entityClass : entities) {
            String fileName = entityClass.getSimpleName().toLowerCase() + ".csv";
            String csvData = generateCsvDataForEntity(entityClass);

            // Add each CSV file to the ZIP
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(csvData.getBytes());
            zipOutputStream.closeEntry();
        }

        zipOutputStream.close();
        byteArrayOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    private String generateCsvDataForEntity(Class<?> entityClass) throws IOException {
        // You need to implement this method to generate CSV data for the specified entityClass
        // For example, you can fetch data from the repository and format it as CSV
        // For demonstration purposes, we'll just create sample CSV data here

        List<String[]> dataRows = new ArrayList<>();

        // Add headers
        String[] headers = getHeadersByEntity(entityClass);
        dataRows.add(headers);

        // Add data rows
        List<?> dataList = getDataListByEntity(entityClass);
        for (Object data : dataList) {
            String[] record = getRecordByEntity(entityClass, data);
            dataRows.add(record);
        }

        // Generate CSV data using Apache Commons CSV
        StringBuilder csvData = new StringBuilder();
        try (CSVPrinter csvPrinter = new CSVPrinter(csvData, CSVFormat.DEFAULT)) {
            for (String[] row : dataRows) {
                csvPrinter.printRecord((Object[]) row);
            }
        }

        return csvData.toString();
    }

    private void createZipFile(String zipFilePath, List<String> fileNames) throws IOException {
        byte[] buffer = new byte[1024];

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String fileName : fileNames) {
                String filePath = Paths.get(System.getProperty("user.home"), fileName).toString();
                File file = new File(filePath);

                ZipEntry ze = new ZipEntry(fileName);
                zos.putNextEntry(ze);

                try (FileInputStream fis = new FileInputStream(file)) {
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                }

                zos.closeEntry();
            }
        }
    }
    // Helper methods for fetching data, headers, and records based on the entity
    private List<?> getDataListByEntity(Class<?> entityClass) {
        if (entityClass.equals(User.class)) {
            return userRepository.findAll();
        } else if (entityClass.equals(Channel.class)) {
            return channelRepository.findAll();
        } else if(entityClass.equals(Guest.class)){
            return guestRepository.findAll();
        }
        else {
            // Add more entity-specific cases here if needed
            return Collections.emptyList();
        }
    }
    private String[] getHeadersByEntity(Class<?> entityClass) {
        if (entityClass.equals(User.class)) {
            return new String[]{"User ID", "First Name", "Last Name", "Email"};
        } else if (entityClass.equals(Channel.class)) {
            return new String[]{"Channel ID", "Carrier Number"};
        } else if (entityClass.equals(Guest.class)){
            return new String[]{"Guest ID", "ChannelId", "type", "lavel", "amount" };
        } else {
            // Add more entity-specific cases here if needed
            return new String[]{};
        }
    }
    private String[] getRecordByEntity(Class<?> entityClass, Object data) {
        if (entityClass.equals(User.class)) {
            User user = (User) data;
            return new String[]{String.valueOf(user.getId()), user.getFirstName(), user.getLastName()};
        } else if (entityClass.equals(Channel.class)) {
            Channel channel = (Channel) data;
            return new String[]{channel.getId()};
        }else if(entityClass.equals(Guest.class)){
            Guest guest = (Guest) data;
            return new String[]{String.valueOf(guest.getId()),};
        }else {
            // Add more entity-specific cases here if needed
            return new String[]{};
        }
    }
}
