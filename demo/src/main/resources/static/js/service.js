// Function to fetch available services and populate the select dropdown
async function loadAvailableServices() {
    const hotelID = document.getElementById("HotelID").value;
    const url = `/getAvailableServices/${hotelID}`;
    console.log(`Request URL: ${url}`);

    try {
        const token = localStorage.getItem('token');
        const response = await axios.get(url, {
            headers: { Authorization: `Bearer ${token}` }
        });

        console.log('Available services:', response.data);
        populateServiceDropdown(response.data);
    } catch (error) {
        console.error("Error loading available services:", error);
        alert("An error occurred while loading available services.");
    }
}

// Function to populate the service dropdown
function populateServiceDropdown(services) {
    const serviceDropdown = document.getElementById("service");
    serviceDropdown.innerHTML = '<option value="">Select Service</option>'; // Clear options with placeholder

    if (services && services.length > 0) {
        services.forEach(service => {
            const option = document.createElement("option");
            option.value = service.serviceID;
            option.textContent = service.serviceName;
            option.dataset.price = service.price; // Store price as data attribute
            serviceDropdown.appendChild(option);
        });
    } else {
        const option = document.createElement("option");
        option.value = "";
        option.textContent = "No services available";
        serviceDropdown.appendChild(option);
    }
}

// Function to update the cost when a service is selected
function updateServiceCost() {
    const serviceDropdown = document.getElementById("service");
    const selectedOption = serviceDropdown.options[serviceDropdown.selectedIndex];
    const cost = selectedOption ? selectedOption.dataset.price : "";

    if (cost) {
        document.getElementById("serviceAmount").value = cost;
    } else {
        document.getElementById("serviceAmount").value = "N/A";
    }
}

// Function to set the form to read-only and hide the Check Availability button
function setFormReadOnly(readOnly) {
    const formElements = document.querySelectorAll("#serviceForm input, #serviceForm select, #serviceForm button");
    formElements.forEach(element => {
        if (element.type !== "button" && element.id !== "bookNowBtn") {
            element.readOnly = readOnly;
            element.disabled = readOnly;
        }
    });

    // Hide or show the 'Check Availability' button based on readOnly status
    const checkAvailabilityBtn = document.getElementById("checkAvailabilityBtn");
    if (checkAvailabilityBtn) {
        checkAvailabilityBtn.style.display = readOnly ? "none" : "inline-block"; // Hide button when form is read-only
        checkAvailabilityBtn.disabled = readOnly; // Disable button if form is read-only
    }
}

// Modified checkServiceAvailability function
async function checkServiceAvailability() {
    const serviceID = document.getElementById("service").value;
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;

    if (!serviceID || !date || !time) {
        alert("Please select a service, date, and time.");
        return;
    }

    const data = { serviceID: serviceID, time: time, date: date };

    try {
        const token = localStorage.getItem('token');
        const response = await axios.post('/checkstatus', data, {
            headers: { Authorization: `Bearer ${token}` }
        });

        if (response.data.available === 'Yes') {
            displayServiceAmount(response.data.amount);
            document.getElementById("bookNowBtn").dataset.serviceRequestID = response.data.requestid;

            // Make the form read-only
            setFormReadOnly(true);
            document.getElementById("bookNowBtn").style.display = "inline";
        } else {
            alert(response.data.message || "The requested time is not available. Please select a different time.");
        }
    } catch (error) {
        console.error("Error checking service availability:", error);
        alert("An error occurred while checking service availability.");
    }
}

// Function to display service amount and show "Book Now" button
function displayServiceAmount(amount) {
    document.getElementById("serviceAmount").value = amount;
    document.getElementById("bookNowBtn").style.display = "inline";
}

// Function to handle booking when "Book Now" is clicked
function bookService() {
    const serviceRequestID = document.getElementById("bookNowBtn").dataset.serviceRequestID;
    const cost = document.getElementById("serviceAmount").value;
    console.log(serviceRequestID);

    if (!serviceRequestID) {
        alert("Service Request ID not found. Please try checking availability again.");
        return;
    }

    const paymentUrl = `/servicepayment?serviceRequestID=${encodeURIComponent(serviceRequestID)}&cost=${encodeURIComponent(cost)}`;
    window.location.href = paymentUrl;
}

// Add event listener to update cost when a service is selected
document.getElementById("service").addEventListener("change", updateServiceCost);

// Check if user is logged in
function checkuser() {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('User not logged in. Redirecting to login page.');
        window.location.href = '/login';
        return;
    }
}

window.addEventListener('load', checkuser);
window.addEventListener("DOMContentLoaded", loadAvailableServices);
document.getElementById("checkAvailabilityBtn").addEventListener("click", checkServiceAvailability);
document.getElementById("bookNowBtn").addEventListener("click", bookService);

// Function to display service amount and show "Book Now" button
function displayServiceAmount(amount) {
    document.getElementById("serviceAmount").value = amount;
    document.getElementById("bookNowBtn").style.display = "inline";
}

// Function to handle booking when "Book Now" is clicked
function bookService() {
    const serviceRequestID = document.getElementById("bookNowBtn").dataset.serviceRequestID;
    const cost = document.getElementById("serviceAmount").value;
    console.log(serviceRequestID);

    if (!serviceRequestID) {
        alert("Service Request ID not found. Please try checking availability again.");
        return;
    }

    const paymentUrl = `/servicepayment?serviceRequestID=${encodeURIComponent(serviceRequestID)}&cost=${encodeURIComponent(cost)}`;
    window.location.href = paymentUrl;
}


// Add event listener to update cost when a service is selected
document.getElementById("service").addEventListener("change", updateServiceCost);
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