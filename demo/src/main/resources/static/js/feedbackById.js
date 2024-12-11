function fetchFeedback() {
    const apiUrl = 'http://localhost:8080/feedbackById';
    const token = localStorage.getItem('token');

    // Check if the user is not logged in
    if (!token) {
        alert('Please kindly login to see your feedbacks.');
        window.location.href = '/login'; // Redirect to login page
        return; // Exit the function early
    }

    // If logged in, proceed with fetching feedback
    axios.get(apiUrl, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
    .then((response) => {
        console.log(response);
        const data = response.data;
        console.log(data);
        const feedbackTableBody = document.getElementById('feedbackTableBody');
        feedbackTableBody.innerHTML = '';

        data.forEach((feedback, index) => {
            const row = document.createElement('tr');

            // Construct the image URL based on the hotelNo and image path
            const imageUrls = feedback.image || [];
            const imageThumbnails = imageUrls.length > 0 ? imageUrls.map(imageUrl => `
                <img src="images/${feedback.hotelNo}/${imageUrl}" alt="Feedback Image" class="thumbnail"
                     onclick="showImageModal('images/${feedback.hotelNo}/${imageUrl}')" style="width: 50px; height: auto;">
            `).join('') : '<img src="path/to/placeholder/image.png" alt="No Image" class="thumbnail" style="width: 50px; height: auto;">';

            row.innerHTML = `
                <th scope="row">${index + 1}</th>
                <td>${feedback.reviewId}</td>
                <td>${feedback.hotelNo}</td>
                <td><input type="number" id="ratings-${index}" value="${feedback.ratings}" readonly></td>
                <td><input type="text" id="comments-${index}" value="${feedback.comments}" readonly></td>
                <td>${feedback.date}</td>
                <td>${feedback.time}</td>
                <td>
                    <div class="image-thumbnails">
                        ${imageThumbnails}
                    </div>
                </td>
                <td>
                    <button class="btn btn-warning" id="edit-${index}" onclick="editFeedback(${index})">Edit</button>
                    <button class="btn btn-success hidden" id="save-${index}" onclick="saveFeedback(${index}, '${feedback.reviewId}')">Save</button>
                </td>
            `;
            feedbackTableBody.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Error fetching data: ', error);
    });
}

window.addEventListener('load', fetchFeedback);

// Function to save edited feedback
function saveFeedback(index, reviewId) {
    const updatedRatings = document.getElementById(`ratings-${index}`).value;
    const updatedComments = document.getElementById(`comments-${index}`).value;
    const apiUrl = 'http://localhost:8080/editFeedback'; // API to update feedback

    const data = {
        reviewId: reviewId,
        ratings: updatedRatings,
        comments: updatedComments,
    };

    axios.put(apiUrl, data, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
        }
    })
    .then(response => {
        alert('Feedback updated successfully');

        // Make fields read-only again
        document.getElementById(`ratings-${index}`).setAttribute('readonly', true);
        document.getElementById(`comments-${index}`).setAttribute('readonly', true);

        // Hide the Save button and show the Edit button
        document.getElementById(`edit-${index}`).classList.remove('hidden');
        document.getElementById(`save-${index}`).classList.add('hidden');
    })
    .catch(error => {
        console.error('Error updating feedback:', error);
        alert('Failed to update feedback. Please try again.');
    });
}

function showImageModal(imageUrl) {
    const modalImage = document.getElementById('modalImage');
    modalImage.src = imageUrl;

    // Use Bootstrap 5 modal to show the modal
    const modal = new bootstrap.Modal(document.getElementById('imageModal'));
    modal.show();
}


function homeFunc() {
    const token = localStorage.getItem('token');

    const loginButton = document.getElementById('loginButton');
    const logoutButton = document.getElementById('logoutButton');
    const profileButton = document.getElementById('profileButton');

    if (token) {
      logoutButton.style.display = 'block';
      loginButton.style.display = 'none';
      profileButton.style.display = 'block';
    } else {
      loginButton.style.display = 'block';
      logoutButton.style.display = 'none';
      profileButton.style.display = 'none';
    }
}

window.addEventListener('load', homeFunc());

function logOutUser() {
    localStorage.removeItem('token');
    axios
        .get('http://localhost:8080/logout')
        .then((response) => {
          console.log('User logged out successfully');
          window.location.href = '/login';
        })
        .catch((error) => {
          console.error('Error logging out:', error);
        });
    }
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