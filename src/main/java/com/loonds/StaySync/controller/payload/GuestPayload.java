package com.loonds.StaySync.controller.payload;

import com.loonds.StaySync.model.entity.CheckInOutRecord;


public record GuestPayload(String firstName, String lastName, String mobile, String email, long[] roomIds, int adults, int children, CheckInOutRecord inOutRecord) {
}
