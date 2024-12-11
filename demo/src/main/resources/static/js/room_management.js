async function fetchRoomTypes() {
    const apiUrl = "http://localhost:8080/admin/getRoomTypes";
    try {
        const response = await axios.get(apiUrl, {
            headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
        });
        const data = response.data;
        const roomTypeTableBody = document.getElementById('roomTypeTableBody');
        const roomTypeFilter = document.getElementById("roomTypeFilter");
        roomTypeTableBody.innerHTML = '';

        data.forEach((room, index) => {
            const row = document.createElement('tr');
            row.innerHTML = `<td>${index+1}</td><td>${room.typeName}</td><td>${room.description}</td>`;
            roomTypeTableBody.appendChild(row);

            const option = document.createElement("option");
            option.value = room.typeName;
            option.text = room.typeName;
            roomTypeFilter.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching room types:', error);
    }
}

async function loadRooms() {
    const apiUrl = "http://localhost:8080/admin/getAllRooms";
    try {
        const response = await axios.get(apiUrl, {
            headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
        });
        const data = response.data;
        const tableBody = document.getElementById("roomTableBody");
        const hotelNoFilter = document.getElementById("hotelNoFilter");
        tableBody.innerHTML = '';

        const hotelNos = new Set();
        data.forEach((room, index) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${index+1}</td>
                <td>${room.roomType}</td>
                <td>${room.cost}</td>
                <td>${room.occupancyLimit}</td>
                <td>${room.hotelno}</td>
            `;
            tableBody.appendChild(row);

            // Add unique hotel numbers to the set for filtering
            hotelNos.add(room.hotelno);
        });

        // Populate hotelNoFilter options
        hotelNos.forEach(hotelNo => {
            const option = document.createElement("option");
            option.value = hotelNo;
            option.text = hotelNo;
            hotelNoFilter.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching rooms:', error);
    }
}

function filterRooms() {
    const selectedRoomType = document.getElementById("roomTypeFilter").value;
    const selectedHotelNo = document.getElementById("hotelNoFilter").value;
    const rows = document.querySelectorAll("#roomTableBody tr");

    rows.forEach((row) => {
        const roomType = row.cells[1].textContent;
        const hotelNo = row.cells[4].textContent;

        // Display row if it matches both filters, or if either filter is set to "all"
        if ((selectedRoomType === "all" || roomType === selectedRoomType) &&
            (selectedHotelNo === "all" || hotelNo === selectedHotelNo)) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    });
}

// Load data on window load
window.addEventListener('load', () => {
    fetchRoomTypes();
    loadRooms();
});

function check() {
        const token = localStorage.getItem('token'); // Fetch the token

           // Check if the token is available
           if (!token) {
               alert('User not logged in. Redirecting to login page.');
               window.location.href = '/adminLoginPage'; // Redirect to staff login page
               return;
           }
    }
window.addEventListener('load', check);
