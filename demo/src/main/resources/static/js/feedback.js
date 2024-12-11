document.addEventListener('DOMContentLoaded', function () {

    // Function to validate the form
    function validateForm() {
        const ratings = document.getElementById('ratings').value;
        const comments = document.getElementById('comments').value;

        if (ratings < 1 || ratings > 5) {
            alert("Please provide a rating between 1 and 5.");
            return false;
        }

        if (comments.trim() === "") {
            alert("Please provide your comments.");
            return false;
        }

        return true;
    }

    // Function to handle adding feedback
    function addFeedback(event) {
        event.preventDefault(); // Prevent form from submitting the traditional way

        // Validate the form
        if (!validateForm()) {
            return; // Stop if validation fails
        }

        // Create a FormData object to hold feedback data and files
        const formData = new FormData();
        formData.append('ratings', document.getElementById('ratings').value);
        formData.append('comments', document.getElementById('comments').value);
        formData.append('hotelNo', document.getElementById('hotelNo').value);

        // Add multiple image files to formData
        const fileInput = document.getElementById('images');
        for (let i = 0; i < fileInput.files.length; i++) {
            formData.append('images', fileInput.files[i]);
        }

        const token = localStorage.getItem('token'); // Assuming token is stored in localStorage
        const postApiUrl = 'http://localhost:8080/feedback/add'; // URL for adding feedback

        fetch(postApiUrl, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`
            },
            body: formData
        })
        .then(response => {
            if (response.ok) {
                alert('Feedback added successfully.');
                window.location.href = "/feedback";
            } else if (response.status === 400) {
                alert('Failed to add feedback: Bad request. u have to booking atleast a room or a service .');
            } else if (response.status === 500) {
                alert('Failed to add feedback: Server error. Please try again later.');
            } else {
                alert('Failed to add feedback: Unexpected error occurred.');
            }
        })
        .catch(error => {
            console.error('Error adding feedback:', error);
            alert('An error occurred while submitting your feedback.');
        });
    }

    // Add an event listener to the form submission
    document.getElementById('feedbackForm').addEventListener('submit', addFeedback);
});
function checkuser() {
        const token = localStorage.getItem('token'); // Fetch the token

           // Check if the token is available
           if (!token) {
               alert('User not logged in. Redirecting to login page.');
               window.location.href = '/login'; // Redirect to staff login page
               return;
           }
    }
window.addEventListener('load', checkuser);
