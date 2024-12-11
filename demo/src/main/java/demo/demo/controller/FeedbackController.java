package demo.demo.controller;

import demo.demo.Module.Feedback;
import demo.demo.Module.User;
import demo.demo.Repository.BookingRepository;
import demo.demo.Repository.FeedbackRepository;
import demo.demo.Services.BookingService;
import demo.demo.Services.FeedbackService;
import demo.demo.Services.UserService;
import demo.demo.jsonResponse.EditRequest;
import demo.demo.jsonResponse.FeedbackObjImage;
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
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//import static com.sun.java.swing.action.ActionUtilities.IMAGE_DIR;

@RestController // Now using RestController to return JSON data
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    private final BookingService bookingService;

    private final JwtUtil jwtService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, FeedbackRepository feedbackRepository, UserService userService, BookingService bookingService, JwtUtil jwtService) {
        this.feedbackService = feedbackService;
        this.feedbackRepository = feedbackRepository;
        this.userService = userService;
        this.bookingService = bookingService;

        this.jwtService = jwtService;
    }

    // Get all feedback
    @GetMapping("feedback/list")
    public List<FeedbackObjImage> getAllFeedback() {
        List<FeedbackObjImage> feedbackList = feedbackService.getAllFeedback();

        return feedbackList;
    }
    @GetMapping("feedback/list/{num}")
    public List<Feedback> Feed(@PathVariable int num) {
        List<Feedback> feedbackList = feedbackService.getFeedback(num);

        return feedbackList;
    }

    @GetMapping("/feedbackById")
    public List<FeedbackObjImage> getFeedbackById() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails obj = (UserDetails) auth.getPrincipal();
        String email = obj.getUsername();

        // Retrieve user ID based on email
        UUID userId = userService.getUserByEmail(email).getUserID();
        System.out.println("RESHMA");
        List<FeedbackObjImage> feedbackList = feedbackService.getAllFeedbackByUser(userId);
        return feedbackList;
    }
    @PutMapping("/editFeedback")
    public String editFeedback(@RequestBody feedbackEditRequest er) {

        // Assuming feedback is linked with a user or you verify authorization, retrieve the feedback entry

        // Update the feedback attribute using the feedbackId and provided attribute name & value
        feedbackService.updateFeedbackAttribute(er.getReviewId(),er.getRatings(), er.getComments());

        return "Feedback updated";
    }


    // Get feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable UUID id) {
        Feedback feedback = feedbackService.getFeedbackByID(id);
        if (feedback != null) {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add feedback (with token extraction logic)

    private static final String IMAGE_DIR1= "src/main/resources/static/Images/1/";
    private static final String IMAGE_DIR2= "src/main/resources/static/Images/2/";

    @PostMapping("feedback/add")
    public ResponseEntity<String> addFeedback(
            @RequestParam("ratings") int ratings,
            @RequestParam("comments") String comments,
            @RequestParam("hotelNo") int hotelNo,
            @RequestParam("images") List<MultipartFile> images) {

        // Extract email from token
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails obj = (UserDetails) auth.getPrincipal();
        String email = obj.getUsername();

        // Retrieve user ID based on email
        UUID userId = userService.getUserByEmail(email).getUserID();
        boolean flag=true;
        flag=bookingService.checkuser(userId);
        if(flag) {

            // Create a new Feedback object
            Feedback feedback = new Feedback();
            feedback.setUserNo(userId);
            UUID reviewid = UUID.randomUUID();
            feedback.setReviewId(reviewid);
            feedback.setRatings(ratings);
            feedback.setComments(comments);
            feedback.setHotelNo(hotelNo);
//        private UUID reviewId;
//        private int ratings;
//        private int hotelNo;
//        private UUID userNo;
//        private String comments;
//        private Date date;
//        private Time time;

            // Save feedback data to the database
            feedbackService.addFeedback(feedback);
            String IMAGE_DIR;
            if (hotelNo == 1) {
                IMAGE_DIR = IMAGE_DIR1;
            } else {
                IMAGE_DIR = IMAGE_DIR2;
            }
            // Ensure image directory exists
            File directory = new File(IMAGE_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save each image file to the server
            for (MultipartFile image : images) {
                try {
                    System.out.println(IMAGE_DIR);
                    Path filePath = Paths.get(IMAGE_DIR + image.getOriginalFilename());
                    Path filep = Paths.get(image.getOriginalFilename());
                    feedbackService.addimages(reviewid, filep);

                    Files.write(filePath, image.getBytes());
                    System.out.println("UPLOADING THE IMAGES");

                    // Optionally, store image file paths in database if needed
                    // feedbackService.saveImagePath(feedback.getReviewId(), filePath.toString());

                } catch (IOException e) {
                    return ResponseEntity.status(500).body("Failed to save images");
                }
            }

            return ResponseEntity.ok("Feedback and images added successfully");
        }
        return ResponseEntity.status(400).body("user havent done any booking");
    }

    // Delete feedback by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable UUID id) {
        try {
            feedbackService.deleteFeedback(id);
            return new ResponseEntity<>("Feedback deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting feedback", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/feedback/images/{hotelid}")
    public List<String> getImageList(@PathVariable int hotelid) {
        return feedbackRepository.getFeedbackImages(hotelid);
    }
}