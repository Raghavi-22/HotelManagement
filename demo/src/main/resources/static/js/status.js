function check() {
    const sno = document.getElementById('sno').value;
    const apiUrl = `http://localhost:8080/lostAndFound/list/${sno}`;

    // Check if S.No is provided
    if (!sno) {
        alert('Please enter a valid S.No.');
        return;
    }

    // Fetching lost and found items from the API
    axios.get(apiUrl, { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` } })
        .then((response) => {
            const data = response.data;
            const lostAndFoundTableBody = document.getElementById('lostAndFoundTableBody');

            if (!lostAndFoundTableBody) {
                console.error('Table body element not found');
                return;
            }

            lostAndFoundTableBody.innerHTML = ''; // Clear existing rows
            console.log(data);

            // Check if any items were returned
            if (data.length === 0) {
                alert('No items found for the provided S.No.');
                return;
            }

            // Generate table rows for each item
            data.forEach((item, index) => {
                const row = document.createElement('tr');

                // Generate table row with multiple image thumbnails
                row.innerHTML = `
                    <th scope="row">${index + 1}</th>
                    <td>${item.sNo}</td>
                    <td>${item.itemType}</td>
                    <td>${item.hotelno}</td>
                    <td>${item.foundLocation}</td>
                    <td>${item.status}</td>
                    <td>${item.description}</td>
                    <td>${item.date}</td>
                    <td>${item.time}</td>
                    <td>
                        <div class="image-thumbnails">
                            ${item.image.length > 0 ? item.image.map(imageUrl => `
                                <img src="${imageUrl}" alt="Item Image" class="thumbnail"
                                     onclick="showImageModal('${imageUrl}')">
                            `).join('') : 'No images available'}
                        </div>
                    </td>
                `;

                lostAndFoundTableBody.appendChild(row);
            });
        })
        .catch(error => {
            // Handle error responses from the server
            if (error.response && error.response.status === 401) {
                alert('Please log in and enter the correct S.No.');
            } else {
                alert('An error occurred while fetching the data. Please try again.');
            }
            console.error('Error fetching data:', error);
        });
}

function showImageModal(imageUrl) {
    // Set the src attribute of the modal image to the clicked thumbnail's URL
    const modalImage = document.getElementById('modalImage');
    modalImage.src = imageUrl;

    // Show the modal (Bootstrap's modal)
    const imageModal = new bootstrap.Modal(document.getElementById('imageModal'));
    imageModal.show();
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
