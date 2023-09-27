async function processApiResponse({ responseFunc, response }) {
    if (!response || !("data" in response)) {
        return;
    }
    Object.entries(responseFunc).forEach(([curStatusCode, func]) => {
        if (+curStatusCode === response.data.statusCode || +curStatusCode === response.data.status)
            func(response);
    });
}

export default processApiResponse;
