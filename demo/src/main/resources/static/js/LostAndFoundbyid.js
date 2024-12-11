function fetchLostAndFound() {
    const apiUrl = 'http://localhost:8080/lostAndFound/listbyid';

    axios.get(apiUrl, {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
    })
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

            // Generate table row with editable fields
            row.innerHTML = `
                <th scope="row">${index + 1}</th>
                <td>${item.sNo}</td>
                <td><input type="text" id="itemType-${index}" value="${item.itemType}" readonly></td>
                <td>${item.hotelno}</td>
                <td><input type="text" id="foundLocation-${index}" value="${item.foundLocation}" readonly></td>
                <td><input type="text" id="status-${index}" value="${item.status}" readonly></td>
                <td><input type="text" id="description-${index}" value="${item.description}" readonly></td>
                <td>${item.date}</td>
                <td>${item.time}</td>
                <td>
                    <div class="image-thumbnails">
                        ${item.image && item.image.length > 0 ? item.image.map(imageUrl => `
                            <img src="${imageUrl}" alt="Item Image" class="thumbnail"
                                 onclick="showImageModal('${imageUrl}')" style="width: 50px; height: auto;">
                        `).join('') : '<img src="path/to/placeholder/image.png" alt="No Image" class="thumbnail" style="width: 50px; height: auto;">'}
                    </div>
                </td>
                <td>
                    <button class="btn btn-warning" id="edit-${index}" onclick="editLostAndFound(${index})">Edit</button>
                    <button class="btn btn-success hidden" id="save-${index}" onclick="saveLostAndFound(${index}, '${item.sNo}')">Save</button>
                </td>
            `;

            lostAndFoundTableBody.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });
}

// Function to make fields editable
function editLostAndFound(index) {
    document.getElementById(`itemType-${index}`).removeAttribute('readonly');
    document.getElementById(`foundLocation-${index}`).removeAttribute('readonly');
    document.getElementById(`status-${index}`).removeAttribute('readonly');
    document.getElementById(`description-${index}`).removeAttribute('readonly');

    // Hide the Edit button and show the Save button
    document.getElementById(`edit-${index}`).classList.add('hidden');
    document.getElementById(`save-${index}`).classList.remove('hidden');
}

// Function to save edited Lost and Found data
function saveLostAndFound(index, sNo) {
    const updatedItemType = document.getElementById(`itemType-${index}`).value;
    const updatedFoundLocation = document.getElementById(`foundLocation-${index}`).value;
    const updatedStatus = document.getElementById(`status-${index}`).value;
    const updatedDescription = document.getElementById(`description-${index}`).value;
    const apiUrl = 'http://localhost:8080/lostAndFound/edit'; // API to update lost and found item

    const data = {
        sNo: sNo,
        itemType: updatedItemType,
        foundLocation: updatedFoundLocation,
        status: updatedStatus,
        description: updatedDescription
    };

    axios.put(apiUrl, data, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
        }
    })
    .then(response => {
        alert('Lost and Found item updated successfully');

        // Make fields read-only again
        document.getElementById(`itemType-${index}`).setAttribute('readonly', true);
        document.getElementById(`foundLocation-${index}`).setAttribute('readonly', true);
        document.getElementById(`status-${index}`).setAttribute('readonly', true);
        document.getElementById(`description-${index}`).setAttribute('readonly', true);

        // Hide the Save button and show the Edit button
        document.getElementById(`edit-${index}`).classList.remove('hidden');
        document.getElementById(`save-${index}`).classList.add('hidden');
    })
    .catch(error => {
        console.error('Error updating Lost and Found item:', error);
        alert('Failed to update item. Please try again.');
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
