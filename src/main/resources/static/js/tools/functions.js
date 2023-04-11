const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

function sendRequest(method, url, body = null) {
    return fetch(url).then(response => {
        return response.json()
    })
}