package demo.demo.Services;
import demo.demo.Module.HotelDepartment;
import demo.demo.Module.LostAndFound;
import demo.demo.Repository.HotelDepartmentRepository;
import demo.demo.Repository.LostAndFoundRepository;
import demo.demo.jsonResponse.LostAndFoundObjImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class LostAndFoundService {

    private final LostAndFoundRepository lostAndFoundRepository;

    @Autowired
    public LostAndFoundService(LostAndFoundRepository lostAndFoundRepository) {
        this.lostAndFoundRepository = lostAndFoundRepository;
    }

    public List<LostAndFoundObjImage> getAllItems() {
        return lostAndFoundRepository.getAllItems();
    }

    public int addItem(LostAndFound item) {
       return lostAndFoundRepository.addItem(item);
    }



    public void deleteItem(int sNo) {
        lostAndFoundRepository.deleteItem(sNo);
    }

    public void addimages(int uuid, String filePath) {
        lostAndFoundRepository.addimage(uuid,filePath);
    }

    public void updateAttribute(LostAndFound lostAndFound) {

        lostAndFoundRepository.updateItem(lostAndFound);
    }

    public List<LostAndFoundObjImage> getAllItemsbyid(UUID staffID) {
        return lostAndFoundRepository.getAllItemsbyid(staffID);
    }

    public List<LostAndFoundObjImage> getItem(int sno) {

        return lostAndFoundRepository.getitem(sno);
    }
}