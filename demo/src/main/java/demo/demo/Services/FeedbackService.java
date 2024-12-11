package demo.demo.Services;


import demo.demo.Module.Feedback;
import demo.demo.Repository.FeedbackRepository;
import demo.demo.jsonResponse.FeedbackObjImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public int addFeedback(Feedback feedback) {
        System.out.println("feedbackrepo is calling successfully");
        return feedbackRepository.addFeedback(feedback);
    }

    public Feedback getFeedbackByID(UUID reviewID) {
        return feedbackRepository.getFeedbackByID(reviewID);
    }

    public List<FeedbackObjImage> getAllFeedback() {
        return feedbackRepository.getAllFeedback();
    }

    public int deleteFeedback(UUID reviewID) {
        return feedbackRepository.deleteFeedback(reviewID);
    }

    public List<FeedbackObjImage> getAllFeedbackByUser(UUID userId) {
        return feedbackRepository.getAllFeedbackbyUser(userId);
    }

    public void updateFeedbackAttribute(UUID reviewId, int ratings, String comments) {
        feedbackRepository.updateFeedback(reviewId,ratings,comments);
    }


    public void addimages(UUID reviewid, Path filePath) {
        feedbackRepository.uploadImage(reviewid,filePath);
    }

    public List<Feedback> getFeedback(int num) {
        return feedbackRepository.getFeedback(num);

    }
}