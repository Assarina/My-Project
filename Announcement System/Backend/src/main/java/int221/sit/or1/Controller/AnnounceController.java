package int221.sit.or1.Controller;

import int221.sit.or1.DTO.AnnounceDTO;
import int221.sit.or1.DTO.outputAnnounceDTO;
import int221.sit.or1.DTO.outputHomePageDTO;
import int221.sit.or1.Mapper.ListMapper;
import int221.sit.or1.Services.AnnounceServices;
import int221.sit.or1.Services.CategoryServices;
import int221.sit.or1.entites.Announce;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://127.0.0.1:5173/",
                        "http://ip22or1.sit.kmutt.ac.th",
                        "http://intproj22.sit.kmutt.ac.th/or1"})
@RestController
@RequestMapping("/api")

public class AnnounceController {
    @Autowired
    private AnnounceServices services;

    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    @GetMapping("/announcements")
    public List<outputHomePageDTO> getAnnounces (@RequestParam(defaultValue = "thisAnnounce")String mode){
        List<Announce> list = services.isActiveOrClose(mode);
        return listMapper.mapList(list, outputHomePageDTO.class, modelMapper);
    }
    @GetMapping("/announcements/{id}")
    public outputAnnounceDTO getIdAnnounce(@PathVariable Integer id){
        return modelMapper.map(services.getIdAnnounce(id), outputAnnounceDTO.class);
    }

    @PostMapping("/announcements")
    @ResponseStatus(HttpStatus.OK)
    public outputAnnounceDTO addAnnounceDto(@RequestBody AnnounceDTO newAnnounceDto){

        Announce announce = modelMapper.map(newAnnounceDto, Announce.class);
        announce.setId(null);
        announce.setAnnouncementCategory(categoryServices.getCategoryById(newAnnounceDto.getCategoryId()));

        services.addAnnounce(announce);
        return modelMapper.map(announce,outputAnnounceDTO.class);
    }
    @DeleteMapping("/announcements/{id}")
    public void deleteAnnounce(@PathVariable Integer id) {
        services.deleteAnnounce(id);
    }

    @PutMapping("/announcements/{id}")
    public outputAnnounceDTO updateAnnouncement(@PathVariable Integer id, @RequestBody AnnounceDTO newAnnounceDTO) {
        Announce announce = modelMapper.map(newAnnounceDTO, Announce.class);
        announce.setAnnouncementCategory(categoryServices.getCategoryById(newAnnounceDTO.getCategoryId()));
        services.updateAnnouncement(id, announce);
        return modelMapper.map(announce, outputAnnounceDTO.class);
    }

}

