package com.loonds.StaySync.service;

public interface PdfGenerationService {
    byte[] generateBill(String channelId, String guestId);
}
