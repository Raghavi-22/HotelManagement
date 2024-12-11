function fetchFeedback() {
const apiUrl = 'http://localhost:8080/feedback/list/1';
console.log("hi");
    axios.get(apiUrl)
        .then((response) => {
            console.log(response);
            const data = response.data;
            console.log(data);
            const feedbackTableBody = document.getElementById('feedbackTableBody');
            feedbackTableBody.innerHTML = '';

            data.forEach((feedback, index) => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <th scope="row">${index + 1}</th>
                    <td>${feedback.reviewId}</td>
                    <td>${feedback.userNo}</td>
                    <td>${feedback.ratings}</td>
                    <td>${feedback.comments}</td>
                    <td>${feedback.date}</td>
                    <td>${feedback.time}</td>


                `;
                feedbackTableBody.appendChild(row);
            });
        })
        .catch(error => {
           console.error('Error fetching data: ', error);
       });
}
document.addEventListener("DOMContentLoaded", function () {
    // Fetch and display images for Hotel 1
    fetch('/feedback/images/1')
        .then(response => response.json())
        .then(images => {
            const imageGallery1 = document.getElementById("imageGallery1");
            images.forEach(image => {
                const col = document.createElement("div");
                console.log(image);
                col.className = "col-md-4 mb-4"; // Bootstrap 4: 3 columns per row
                col.innerHTML = `
                    <div class="card h-100">
                        <img src="/images/1/${image}" class="card-img-top" alt="Feedback Image">
                    </div>
                `;
                imageGallery1.appendChild(col);
            });
        })
        .catch(error => {
            console.error("Error fetching images for hotel 1:", error);
        });
});
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
window.addEventListener('load', fetchFeedback);
window.addEventListener('load', homeFunc());