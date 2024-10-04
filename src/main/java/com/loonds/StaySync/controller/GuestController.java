package com.loonds.StaySync.controller;

import com.loonds.StaySync.controller.payload.GuestCheckInPayload;
import com.loonds.StaySync.controller.payload.GuestPayload;
import com.loonds.StaySync.model.dto.GuestDto;
import com.loonds.StaySync.model.dto.OrderDto;
import com.loonds.StaySync.service.GuestService;
import com.loonds.StaySync.service.OrderService;
import com.loonds.StaySync.service.PdfGenerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Guest Rest APIs", description = "API for manage Guest Operations")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class GuestController{
    private final GuestService guestService;
    private final PdfGenerationService pdfGenerationService;
    @PostMapping("/channels/{id}/guests")
    @Operation(summary = "Create a new Guest Given Channel")
    public GuestDto createGuest(@PathVariable String id,
                                @RequestBody GuestPayload payload) {
        return guestService.create(id,payload);
    }


    @PutMapping("/guests/{id}")
    @Operation(summary = "Update given Guest")
    public Optional<GuestDto> updateGuest(@PathVariable String id, @RequestBody GuestDto payload) {
        return guestService.update(id,payload);
    }

    @GetMapping("/guests/{id}")
    @Operation(summary = "Get given Guest Details")
    public Optional<GuestDto> getGuestDetail(@PathVariable String id) {
        return guestService.get(id);
    }

    @GetMapping("/guests/autocomplete")
    @Operation(summary = "Get List of Guest By Query")
    public List<GuestDto> getGuestsListByQuery(@RequestParam(required = true) String query) {
        log.info("Search By Query : {}", query);
        return guestService.autocompleteGuests(query);
    }

    @GetMapping("/channels/{id}/guests")
    @Operation(summary = "Get given Guest Details")
    public Page<GuestDto> getGuestList(@PathVariable String id,
                                       @PageableDefault Pageable pageable) {
        return guestService.getAllGuests(id, pageable);
    }

    @GetMapping("/guests/{id}/bill")
    @Operation(summary = "Get given Guest Total Bill")
    public Double guestTotalBill(@PathVariable String id) {
        return guestService.calculateTotalBill(id);
    }

    @GetMapping("/guests/{id}/generate-bill")
    @Operation(summary = "Create a new Guest Given Channel")
    public ResponseEntity<byte[]> generateBillPdf(@PathVariable String id) {

        byte[] pdfBytes = pdfGenerationService.generateBill("",id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "bill.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

}
