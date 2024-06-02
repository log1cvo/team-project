document.addEventListener('DOMContentLoaded', function() {
    const userForm = document.getElementById('userForm');
    const userInfo = document.getElementById('userInfo');

    userForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(userForm);
        const username = formData.get('username');
        const password = formData.get('password');

        createUser(username, password);
    });

    function createUser(username, password) {
        fetch('http://localhost:8080/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + btoa('admin:admin') // 根据你的 API 认证信息修改
            },
            body: JSON.stringify({
                username: username,
                password: password,
                role: 'USER'
            })
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok.');
            })
            .then(data => {
                fetchUserInfo(username);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }

    function fetchUserInfo(username) {
        fetch(`http://localhost:8080/users/${username}`, {
            method: 'GET',
            headers: {
                'Authorization': 'Basic ' + btoa(`${username}:password`) // 根据实际用户信息修改
            }
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok.');
            })
            .then(data => {
                displayUserInfo(data);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }

    function displayUserInfo(data) {
        userInfo.innerHTML = `
            <p>ID: ${data.id}</p>
            <p>Username: ${data.username}</p>
            <p>Role: ${data.role}</p>
        `;
    }
});
