package com.loonds.StaySync.event;

import com.loonds.StaySync.model.entity.CheckInOutRecord;

public record BookingEvent(String channelId, String guestId, long[] roomIds, CheckInOutRecord inOutRecord) {
}
