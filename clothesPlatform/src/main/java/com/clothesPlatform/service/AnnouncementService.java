package com.clothesPlatform.service;

import com.clothesPlatform.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface AnnouncementService {

    String saveAnnouncement(Announcement announcement);

    Page<Announcement> findAllAnnouncement(int page,int size);

    Announcement findAnnoucement(int announcementId);

    List<Announcement> findAllByOrderByDateDesc();

    List<Announcement> findAnnouncementByDate(String title,@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date end);

    void deleteAnnouncement(int announcementId);
}
