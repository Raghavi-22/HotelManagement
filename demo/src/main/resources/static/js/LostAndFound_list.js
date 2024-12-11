function fetchLostAndFound() {
    const apiUrl = 'http://localhost:8080/lostAndFound/list';

    axios.get(apiUrl, { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }})
        .then((response) => {
            const data = response.data;
            const lostAndFoundTableBody = document.getElementById('lostAndFoundTableBody');

            if (!lostAndFoundTableBody) {
                console.error('Table body element not found');
                return;
            }

            lostAndFoundTableBody.innerHTML = ''; // Clear existing rows
            console.log(data);

            data.forEach((item, index) => {
                const row = document.createElement('tr');

                // Check if an image URL exists; if not, provide a placeholder
                const imageUrl = (item.image && item.image.length > 0) ? item.image[0] : 'path/to/placeholder/image.png';

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
                            <img src="${imageUrl}" alt="Item Image"
                                 style="width: 50px; height: auto;"
                                 onclick="showImageModal('${imageUrl}')">
                        </div>
                    </td>
                `;

                lostAndFoundTableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}

function showImageModal(imageUrl) {
    // Set the src attribute of the modal image to the clicked thumbnail's URL
    const modalImage = document.getElementById('modalImage');
    modalImage.src = imageUrl;

    // Show the modal (Bootstrap's modal)
    $('#imageModal').modal('show');
}

window.addEventListener('load', fetchLostAndFound);
window.addEventListener('load', checkuser);
function checkstaff() {
        const token = localStorage.getItem('token'); // Fetch the token

           // Check if the token is available
           if (!token) {
               alert('User not logged in. Redirecting to login page.');
               window.location.href = '/staff-login'; // Redirect to staff login page
               return;
           }
    }
window.addEventListener('load', checkstaff);
