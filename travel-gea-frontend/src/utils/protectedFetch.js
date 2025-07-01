const fetchProtectedData = async (url, router, method = "GET", body = null) => {
    const token = localStorage.getItem("jwtToken")
    if (!token) {
        router.push("/login-or-signup")
    }

    const options = {
        method,
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    }
    if (body && method !== "GET") {
        options.body = JSON.stringify(body)
    }

    try {
        const res = await fetch(url, options)
        console.log("Response status:", res.status)
        if (res.ok) {
            return await res.json()
        } else {
            console.error("Could not fetch protected data due to invalid token")
            router.push("/login-or-signup")
        }
    } catch (error) {
        console.error(error)
        console.error(
            "Could not fetch protected data due to error response from server"
        )
    }
}

export default fetchProtectedData
