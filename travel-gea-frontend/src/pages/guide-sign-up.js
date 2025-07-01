import { useRouter } from "next/router"

import GuideSignUpForm from "@/components/GuideSignUpForm"
import BlankLayout from "@/components/BlankLayout"

function GuideSignUp() {
    const router = useRouter()

    async function handleGuideCreation(e) {
        e.preventDefault()

        const formData = new FormData(e.currentTarget)
        const guideName = formData.get("guideName")
        const guidePhone = formData.get("guidePhone")
        const guideDescription = formData.get("guideDescription")
        const specialities = formData.getAll("specialities")
        const email = formData.get("email")
        const userName = formData.get("userName")
        const password = formData.get("password")

        const requestData = {
            guideName,
            guidePhone,
            guideImageUrl: "whatever",
            guideDescription,
            specialities,
            email,
            userName,
            password,
        }

        try {
            const res = await fetch("http://localhost:9030/api/guide/create", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(requestData),
            })
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
                <h1 className="text-3xl font-bold mb-4">Guide Sign Up</h1>
                <GuideSignUpForm handleSubmit={handleGuideCreation} />
            </div>
        </BlankLayout>
    )
}

export default GuideSignUp
