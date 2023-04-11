sendRequest("GET", "/api/user")
    .then(data => {
            document.querySelector("#userPageInfo").innerHTML = `
                        <tr>
                            <td>${data["id"]}</td>
                            <td>${data["firstName"]}</td>
                            <td>${data["lastName"]}</td>
                            <td>${data["age"]}</td>
                            <td>${data["email"]}</td>
                            <td>${data["roles"].map(el => el.role)}</td>
                        </tr>
        `
        }
    )