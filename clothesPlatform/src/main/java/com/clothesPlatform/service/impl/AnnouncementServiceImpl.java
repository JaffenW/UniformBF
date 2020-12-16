package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.Announcement;
import com.clothesPlatform.entity.Order;
import com.clothesPlatform.repository.AnnouncementRepository;
import com.clothesPlatform.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;

    @Override
    public String saveAnnouncement(Announcement announcement) {
        Announcement announcement1 = announcementRepository.save(announcement);
        if (announcement1 != null) {
            return "success";
        }else {
            return "error";
        }
    }

    @Override
    public Page<Announcement> findAllAnnouncement(int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return announcementRepository.findAll(pageable);
    }

    @Override
    public Announcement findAnnoucement(int announcementId) {
        return announcementRepository.findById(announcementId).get();
    }

    @Override
    public List<Announcement> findAllByOrderByDateDesc() {
        return announcementRepository.findAllByOrderByDateDesc();
    }

    @Override
    public List<Announcement> findAnnouncementByDate(String title,@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return announcementRepository.findAnnoucementByDate("%"+title+"%",start, end);
    }

    @Override
    public void deleteAnnouncement(int announcementId) {
        announcementRepository.deleteById(announcementId);
    }
}
