import { useRouter } from "next/router"

import TouristSignUpForm from "@/components/TouristSignUpForm"
import BlankLayout from "@/components/BlankLayout"

function TouristSignUp() {
    const router = useRouter()

    async function handleTouristCreation(e) {
        e.preventDefault()

        const formData = new FormData(e.currentTarget)

        const touristName = formData.get("touristName")
        const touristDescription = formData.get("touristDescription")
        const interests = formData.getAll("interests")
        const email = formData.get("email")
        const userName = formData.get("userName")
        const password = formData.get("password")

        const requestData = {
            touristName,
            touristDescription,
            interests,
            email,
            userName,
            password,
        }

        try {
            const res = await fetch(
                "http://localhost:9030/api/tourist/create",
                {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(requestData),
                }
            )
            if (res.ok) {
                const data = await res.json()
                console.log(data)
                console.log("Sign Up successful. Please login now.")
                router.push("/login") // redirect to login
            } else {
                console.error("Could not login due to invalid credentials")
            }
        } catch (error) {
            console.error(error)
            console.error("Could not login due to error response from server")
        }
    }

    return (
        <BlankLayout>
            <div className="w-9/10 mx-auto">
                <h1 className="text-3xl font-bold mb-4">Tourist Sign Up</h1>
                <TouristSignUpForm handleSubmit={handleTouristCreation} />
            </div>
        </BlankLayout>
    )
}

export default TouristSignUp
