function fetchFeedback() {
    const apiUrl = 'http://localhost:8080/feedback/list';
    axios.get(apiUrl)
        .then((response) => {
            console.log(response);
            const data = response.data;
            console.log(data);
            const feedbackTableBody = document.getElementById('feedbackTableBody');
            feedbackTableBody.innerHTML = '';

            data.forEach((feedback, index) => {
                // Construct image URLs for each feedback item
                const imageUrls = feedback.image || [];
               const imageThumbnails = imageUrls.length > 0 ? imageUrls.map(imageUrl => `
                              <img src="images/${feedback.hotelNo}/${imageUrl}" alt="Feedback Image" class="thumbnail"
                                   onclick="showImageModal('images/${feedback.hotelNo}/${imageUrl}')" style="width: 50px; height: auto;">
                          `).join('') : '<img src="path/to/placeholder/image.png" alt="No Image" class="thumbnail" style="width: 50px; height: auto;">';

                // Create a table row for each feedback item
                const row = document.createElement('tr');

                row.innerHTML = `
                    <th scope="row">${index + 1}</th>
                    <td>${feedback.reviewId}</td>
                    <td>${feedback.hotelNo}</td>
                    <td>${feedback.userNo}</td>
                    <td>${feedback.ratings}</td>
                    <td>${feedback.comments}</td>
                    <td>${feedback.date}</td>
                    <td>${feedback.time}</td>
                    <td>
                        <div class="image-thumbnails">
                            ${imageThumbnails}
                        </div>
                    </td>
                `;
                feedbackTableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching data: ', error);
        });
}

document.getElementById("reviewImagesBtn").addEventListener("click", function () {
    window.location.href = 'feedbackimages';
});

window.addEventListener('load', fetchFeedback);
// Function to show the image in the modal
function showImageModal(imageUrl) {
    const modalImage = document.getElementById('modalImage');
    modalImage.src = imageUrl;

    // Use Bootstrap 5 modal to show the modal
    const modal = new bootstrap.Modal(document.getElementById('imageModal'));
    modal.show();
}





