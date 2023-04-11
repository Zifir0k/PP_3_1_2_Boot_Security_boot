function adminInfo() {
    fetch(urlUser).then(res => {
        res.json().then(data => {
            document.querySelector("#adminPageInfo").innerHTML = `   
            <tr>
                <td>${data["id"]}</td>
                <td>${data["firstName"]}</td>
                <td>${data["lastName"]}</td>
                <td>${data["age"]}</td>
                <td>${data["email"]}</td>
                <td>${data["roles"].map(el => el.role)}</td>               
            </tr>
        `;
        })
    })
}

adminInfo()