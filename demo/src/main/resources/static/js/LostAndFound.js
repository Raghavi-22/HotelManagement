document.addEventListener('DOMContentLoaded', function () {

    // Function to validate form
    function validateForm() {
        const itemType = document.getElementById('itemType').value;
        const foundLocation = document.getElementById('foundLocation').value;
        const description = document.getElementById('description').value;
        const hotelNo = document.getElementById('hotelNo').value;

        if (itemType.trim() === "") {
            alert("Please specify the item type.");
            return false;
        }
        if (foundLocation.trim() === "") {
            alert("Please provide the location where the item was found.");
            return false;
        }
        if (description.trim() === "") {
            alert("Please provide a description.");
            return false;
        }
        if (hotelNo.trim() === "") {
            alert("Please provide a hotel number.");
            return false;
        }

        return true;
    }

    // Function to handle adding lost and found item
    function addLostAndFound(event) {
        event.preventDefault();

        if (!validateForm()) return;

        // Create FormData to hold all form data, including files
        const formData = new FormData();
        formData.append('itemType', document.getElementById('itemType').value);
        formData.append('foundLocation', document.getElementById('foundLocation').value);
        formData.append('status', document.getElementById('status').value);
        formData.append('description', document.getElementById('description').value);
        formData.append('hotelNo', document.getElementById('hotelNo').value);

        // Add each selected image file to the FormData object
        const fileInput = document.getElementById('images');
        for (let i = 0; i < fileInput.files.length; i++) {
            formData.append('images', fileInput.files[i]);
        }

        const token = localStorage.getItem('token');
        const postApiUrl = 'http://localhost:8080/lostAndFound/add';

        fetch(postApiUrl, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`
            },
            body: formData
        })
        .then(response => {
            if (response.ok) {
                alert('Item added to Lost and Found successfully.');
                window.location.href = "/LostAndFound";
            } else {
                alert('Failed to add item.');
            }
        })
        .catch(error => {
            console.error('Error adding item:', error);
            alert('An error occurred while adding the item.');
        });
    }

    document.getElementById('lostAndFoundForm').addEventListener('submit', addLostAndFound);
});
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
