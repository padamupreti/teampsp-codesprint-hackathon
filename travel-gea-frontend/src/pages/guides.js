import { useRouter } from "next/router"
import { useState, useEffect } from "react"

import fetchProtectedData from "@/utils/protectedFetch"
import Layout from "@/components/Layout"
import GuideCardD from "@/components/GuideCardD"

function Guides({ usersJson }) {
    const router = useRouter()

    const [guides, setGuides] = useState(null)

    useEffect(() => {
        async function fetchUsers() {
            let resData = await fetchProtectedData(
                "http://localhost:9030/api/guide/all"
            )
            console.log(resData)
            if (resData) setGuides(resData.detail.guides)
            else router.push("/login")
        }
        fetchUsers()
    }, [router])

    if (guides) {
        return (
            <Layout>
                <h1 className="text-3xl font-bold mb-4">Guides</h1>
                {guides.map((guide) => (
                    <GuideCardD key={guide.id} guide={guide} />
                ))}
            </Layout>
        )
    } else return <p>Loading ...</p>
}

export default Guides
