function validateDates() {
    const hotelId = document.getElementById("hotelId").value;
    const checkInDate = document.getElementById("checkInDate").value;
    const checkOutDate = document.getElementById("checkOutDate").value;
    const today = new Date();

    // Check if Hotel ID is empty
    if (!hotelId) {
        alert("Hotel ID is a required field.");
        return false;
    }

    // If either date is empty, show an alert
    if (!checkInDate || !checkOutDate) {
        alert("Both Check-In and Check-Out dates are required.");
        return false;
    }

    // Convert input date strings to Date objects
    const checkIn = new Date(checkInDate);
    const checkOut = new Date(checkOutDate);

    // Remove time part from today's date to compare just the date
    today.setHours(0, 0, 0, 0);

    // Check if check-in date is before today or check-out is before check-in
    if (checkIn < today || checkIn >= checkOut) {
        alert("Invalid date selection. Please check your dates.");
        return false;
    }

    return true;
}

function toggleFormElements(isDisabled) {
    // Get all inputs and buttons on the form
    const elements = document.querySelectorAll("input, button");
    elements.forEach(element => {
        element.disabled = isDisabled;
    });
}
function checkAvailability() {
    // Check if the user is logged in by looking for the token
    const token = localStorage.getItem('token');
    if (!token) {
        alert("You need to log in to check room availability.");
        window.location.href = '/login'; // Redirect to the login page if not logged in
        return;
    }

    const checkInDate = document.getElementById("checkInDate").value;
    const checkOutDate = document.getElementById("checkOutDate").value;
    const roomType = document.getElementById("roomType").value;
    const numOfGuests = document.getElementById("numOfGuests").value;
    const hotelId = document.getElementById("hotelId").value;

    if (validateDates()) {
        const apiUrl = 'http://localhost:8080/dashboard/check';
        const data = {
            checkInDate: checkInDate,
            checkOutDate: checkOutDate,
            roomType: roomType,
            noOfGuests: parseInt(numOfGuests, 10),
            hotelId: hotelId,
        };

        axios.post(apiUrl, data, { headers: { Authorization: `Bearer ${token}` } })
            .then((response) => {
                console.log(response);

                // Assuming response.data has an 'available' property indicating availability
                if (response && response.data === "Rooms Available") {
                    alert("The required rooms are available! Please click on Book Now to confirm your booking.");

                    // Disable all inputs and buttons except "Book Now"
                    document.getElementById("hotelId").readOnly = true;
                    document.getElementById("checkInDate").readOnly = true;
                    document.getElementById("checkOutDate").readOnly = true;
                    document.getElementById("roomType").disabled = true;
                    document.getElementById("numOfGuests").disabled = true;

                    // Hide "Check Availability" button and enable "Book Now" button
                    document.querySelector('button[onclick="checkAvailability()"]').style.display = "none";
                    document.getElementById("bookNowButton").disabled = false;
                    document.getElementById("bookNowButton").style.display = "inline-block";
                } else {
                    alert("Sorry! The rooms you require are currently not available.");
                }
            })
            .catch((error) => {
                console.error('Error fetching availability:', error);
                alert("Sorry for the inconvenience. Requested Room is not available");
            });
    }
}


function logOutUser() {
    localStorage.removeItem('token');
    axios.get('http://localhost:8080/logout')
        .then(() => {
            console.log('User logged out successfully');
            window.location.href = '/login';
        })
        .catch((error) => {
            console.error('Error logging out:', error);
            alert("Failed to log out. Please try again.");
        });
}

function bookNow() {
    const checkInDate = document.getElementById("checkInDate").value;
    const checkOutDate = document.getElementById("checkOutDate").value;
    const roomType = document.getElementById("roomType").value;
    const numOfGuests = document.getElementById("numOfGuests").value;
    const hotelId = document.getElementById("hotelId").value;
    const n = parseInt(numOfGuests, 10);

    console.log(n);
    console.log(typeof(n));
    console.log("hotel id:", hotelId);

    const apiUrl = 'http://localhost:8080/dashboard/book';
    const data = {
        checkInDate: checkInDate,
        checkOutDate: checkOutDate,
        roomType: roomType,
        noOfGuests: n,
        hotelId: hotelId,
    };

    axios.post(apiUrl, data, { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }})
        .then((response) => {
            alert("KINDLY PROCEED TO PAYMENT");

            // Extract data from the response
            const { message, roomRequestID, cost } = response.data;

            // Construct the URL with query parameters
            const paymentUrl = `/payment?roomRequestID=${encodeURIComponent(roomRequestID)}&cost=${encodeURIComponent(cost)}`;

            // Redirect to the payment page with the parameters
            window.location.href = paymentUrl;
        })
        .catch((error) => {
            alert("Some error occurred!! Please try again later");
            console.error('Error fetching user profile data:', error);
        });
}
function toggleServiceRequest() {
    const serviceRequestForm = document.getElementById("serviceRequestForm");

    // Toggle the visibility
    serviceRequestForm.style.display = (serviceRequestForm.style.display === "none" || !serviceRequestForm.style.display) ? "block" : "none";
}
function homFunc() {
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

window.addEventListener('load', homFunc());
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

