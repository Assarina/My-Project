package int221.sit.or1.Services;

import int221.sit.or1.entites.Announce;
import int221.sit.or1.repositories.AnnounceRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class AnnounceServices {
    @Autowired
    private AnnounceRepositories announcement;

    public List<Announce> findAll() {
        List<Announce> announces = announcement.findAll(Sort.by(Sort.Direction.DESC, "publishDate", "closeDate"));
        return announces;
    }

    public Announce getIdAnnounce(Integer id) {
        return announcement.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id" + id + " does not exist!!!"));
    }
    public Announce addAnnounce(Announce announce) {
        return announcement.saveAndFlush(announce);
    }

    public void deleteAnnounce(Integer id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id" + id + "cannot be null");
        }

        Optional<Announce> optionalAnnounce = announcement.findById(id);
        if (optionalAnnounce.isPresent()) {
            Announce selected = optionalAnnounce.get();
            announcement.delete(selected);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id: " + id + " does not exist");
        }
    }

    public Announce updateAnnouncement(Integer id, Announce announce) {
        Announce newAnnounce = announcement.findById(id).orElseThrow(() ->
                new ResourceAccessException(id + " does not exist!"));
        newAnnounce.setAnnouncementTitle(announce.getAnnouncementTitle());
        newAnnounce.setAnnouncementDescription(announce.getAnnouncementDescription());
        newAnnounce.setPublishDate(announce.getPublishDate());
        newAnnounce.setCloseDate(announce.getCloseDate());
        newAnnounce.setAnnouncementDisplay(announce.getAnnouncementDisplay());
        if (announce.getAnnouncementCategory() != null) {
            newAnnounce.setAnnouncementCategory(announce.getAnnouncementCategory());
        }
        return announcement.saveAndFlush(newAnnounce);
    }

    public List<Announce> isActiveOrClose(String mode){
        if (mode.equals("active")) {
            List<Announce> announces = new ArrayList<>();
            announces.addAll(announcement.findAnnouncementsBothDateIsNullAndY());
            announces.addAll(announcement.findAnnouncementsPublishDateNotnullAndCurrentAndY(ZonedDateTime.parse(ZonedDateTime.now().toString())));
            announces.addAll(announcement.findAnnouncementsByOpenAndCloseDateAndDisplayY(ZonedDateTime.parse(ZonedDateTime.now().toString())));
            announces.addAll(announcement.findAnnouncementsByCloseDateAndDisplayY(ZonedDateTime.parse(ZonedDateTime.now().toString())));

            Collections.sort(announces, Comparator.comparingLong(Announce::getId).reversed());
            return announces;
        } else if (mode.equals("close")) {
            List<Announce> announces = new ArrayList<>();
            announces.addAll(announcement.findAnnouncementsByNullDatesAndDisplayN());
            announces.addAll(announcement.findAnnouncementsByClosedDateAndDisplayY(ZonedDateTime.parse(ZonedDateTime.now().toString())));
            announces.addAll(announcement.findAnnouncementsByBeforeCurrentAndClosedDateAndDisplayY(ZonedDateTime.parse(ZonedDateTime.now().toString())));

            Collections.sort(announces, Comparator.comparingLong(Announce::getId).reversed());
            return announces;
        } else {
            return findAll();
        }
    }
}

