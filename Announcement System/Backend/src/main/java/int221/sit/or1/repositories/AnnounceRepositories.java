package int221.sit.or1.repositories;

import int221.sit.or1.entites.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;


public interface AnnounceRepositories extends JpaRepository<Announce,Integer> {

    @Query("SELECT a FROM Announce a WHERE " +
            "(a.publishDate IS NULL AND a.closeDate IS NULL AND a.announcementDisplay = 'Y')")
    List<Announce> findAnnouncementsBothDateIsNullAndY();

    @Query("SELECT a FROM Announce a WHERE " +
            "(a.publishDate IS NOT NULL AND a.closeDate IS NULL AND a.announcementDisplay = 'Y') " +
            "AND :currentDateTime >= a.publishDate")
    List<Announce> findAnnouncementsPublishDateNotnullAndCurrentAndY( ZonedDateTime currentDateTime);

    @Query("SELECT a FROM Announce a WHERE " +
            "(a.publishDate IS NOT NULL AND a.closeDate IS NOT NULL AND a.announcementDisplay = 'Y') " +
            "AND :currentDateTime >= a.publishDate AND :currentDateTime < a.closeDate")
    List<Announce> findAnnouncementsByOpenAndCloseDateAndDisplayY( ZonedDateTime currentDateTime);

    @Query("SELECT a FROM Announce a WHERE " +
            "(a.publishDate IS NULL AND a.closeDate IS NOT NULL AND a.announcementDisplay = 'Y') " +
            "AND :currentDateTime < a.closeDate")
    List<Announce> findAnnouncementsByCloseDateAndDisplayY(ZonedDateTime currentDateTime);

    @Query("SELECT a FROM Announce a WHERE " +
            "(a.publishDate IS NULL AND a.closeDate IS NULL AND a.announcementDisplay = 'N')")
    List<Announce> findAnnouncementsByNullDatesAndDisplayN();

    @Query("SELECT a FROM Announce a WHERE " +
            "(a.publishDate IS NULL AND a.closeDate IS NOT NULL AND a.announcementDisplay = 'Y') " +
            "AND :currentDateTime >= a.closeDate")
    List<Announce> findAnnouncementsByClosedDateAndDisplayY( ZonedDateTime currentDateTime);

    @Query("SELECT a FROM Announce a WHERE " +
            "(a.publishDate IS NOT NULL AND a.closeDate IS NOT NULL AND a.announcementDisplay = 'Y') " +
            "AND :currentDateTime >= a.publishDate AND :currentDateTime >= a.closeDate")
    List<Announce> findAnnouncementsByBeforeCurrentAndClosedDateAndDisplayY( ZonedDateTime currentDateTime);

}
