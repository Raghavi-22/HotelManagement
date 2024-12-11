package demo.demo.controller;

import demo.demo.Module.LostAndFound;
import demo.demo.Module.Staff;
import demo.demo.Services.LostAndFoundService;
import demo.demo.Services.StaffDashboardService;
import demo.demo.Services.StaffService;
import demo.demo.jsonResponse.LostAndFoundObjImage;
import demo.demo.jsonResponse.feedbackEditRequest;
import demo.demo.securityConfiguration.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lostAndFound")
public class LostAndFoundController {

    private final LostAndFoundService lostAndFoundService;
    private final JwtUtil jwtService;
    private final StaffDashboardService staffDashboardService;
    private final StaffService staffService;

    @Autowired
    public LostAndFoundController(LostAndFoundService lostAndFoundService, JwtUtil jwtService, StaffDashboardService staffDashboardService, StaffService staffService) {
        this.lostAndFoundService = lostAndFoundService;
        this.jwtService = jwtService;
        this.staffDashboardService = staffDashboardService;
        this.staffService = staffService;
    }

    @GetMapping("/list")
    public List<LostAndFoundObjImage> getAllItems() {
        System.out.println("controller get all items func called");
        return lostAndFoundService.getAllItems();
    }
    @GetMapping("/list/{sno}")
    public List<LostAndFoundObjImage> getItem(@PathVariable int sno) {
        System.out.println("controller get all items func called");
        return lostAndFoundService.getItem(sno);
    }
    @GetMapping("/listbyid")
    public List<LostAndFoundObjImage> getAllbyItems() {
        System.out.println("controller get all items func called");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String pEmail = userDetails.getUsername();
        System.out.println("Email: " + pEmail);

        // Fetch staff details by email
        Staff staff = staffService.getStaffByEmail(pEmail);


        List<LostAndFoundObjImage> dummy= lostAndFoundService.getAllItemsbyid(staff.getStaffID());
        System.out.println(dummy.size());
        return dummy;

    }
    @PutMapping("/edit")
    public String editFeedback(@RequestBody LostAndFound lostAndFound) {

        // Assuming feedback is linked with a user or you verify authorization, retrieve the feedback entry

        // Update the feedback attribute using the feedbackId and provided attribute name & value

//        lostAndFound.setHandledBy(staff.getStaffID());
        lostAndFoundService.updateAttribute(lostAndFound);

        return "Feedback updated";
    }

    private static final String IMAGE_DIR= "src/main/resources/static/Images/lost_images/";
    @PostMapping("/add")
    public ResponseEntity<String> addLostAndFoundItem(
            @RequestParam("itemType") String itemType,
            @RequestParam("foundLocation") String foundLocation,
            @RequestParam("status") String status,
            @RequestParam("description") String description,
            @RequestParam("hotelNo") int hotelNo,
            @RequestParam("images") List<MultipartFile> images) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails obj = (UserDetails) auth.getPrincipal();
        String email = obj.getUsername();

        // Ensure the image directory exists
        File directory = new File(IMAGE_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a new LostAndFound item
        LostAndFound item = new LostAndFound();
        UUID staffId = staffDashboardService.getStaffByEmail(email).getStaffID();
        item.setHandledBy(staffId);
        item.setDescription(description);
        item.setFoundLocation(foundLocation);
        item.setHotelno(hotelNo);
        item.setItemType(itemType);
        item.setStatus(status);

        // Save the item to the database
       int a= lostAndFoundService.addItem(item);
       System.out.println(a);
        // Save each image file to the server and store the path in the database
        List<String> errors = new ArrayList<>();
        for (MultipartFile image : images) {
            try {
                // Construct the file path
                Path filePath = Paths.get(IMAGE_DIR, image.getOriginalFilename());

                // Write the file to the server
                Files.write(filePath, image.getBytes());
                System.out.println("UPLOADING THE IMAGE: " + image.getOriginalFilename());

                // Save the image path in the database
                lostAndFoundService.addimages(a, "images/lost_images/" + image.getOriginalFilename());


            } catch (IOException e) {
                String errorMsg = "Failed to save image: " + image.getOriginalFilename() + ". Error: " + e.getMessage();
                errors.add(errorMsg);
                System.err.println(errorMsg);
            }
        }

        // Check if any errors occurred during image upload
        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lost and Found item added, but some images failed to upload: " + String.join(", ", errors));
        }

        // Return success message if all images were saved successfully
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Lost and Found item added successfully.");
    }

//    @PutMapping("/edit/{id}")
//    public String editLostAndFoundItem(@PathVariable UUID id, @RequestBody LostAndFound item) {
//        lostAndFoundService.updateItem(id, item);
//        return "Item updated successfully.";
//    }

    @DeleteMapping("/delete/{id}")
    public String deleteLostAndFoundItem(@PathVariable int id) {
        lostAndFoundService.deleteItem(id);
        return "Item deleted successfully.";
    }
}