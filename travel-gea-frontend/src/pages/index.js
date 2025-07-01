import { useRouter } from "next/router"
import { useEffect, useState } from "react"

import Layout from "@/components/Layout"
import EventCard from "@/components/EventCard"
import GuideCard from "@/components/GuideCard"

function Home() {
    const router = useRouter()
    const [user, setUser] = useState(null)
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        const jsonData = localStorage.getItem("userData")
        if (jsonData) {
            setUser(JSON.parse(jsonData))
        }
        setLoading(false)
    }, [])

    // <div className="">
    //             {user && (
    //                 <span className="text-2xl font-bold">
    //                     Hello {user.detail.userName}
    //                 </span>
    //             )}
    //         </div>

    if (loading) {
        return <div>Loading ...</div>
    } else {
        if (!user) router.push("/login")
        else {
            return (
                <Layout>
                    <h2 className="font-bold text-3xl mb-4">Guides</h2>
                    <div className="grid grid-cols-2 gap-4 mb-4">
                        <GuideCard />
                        <GuideCard />
                    </div>
                    <h2 className="font-bold text-3xl mb-4">Events</h2>
                    <div className="grid grid-cols-2 gap-4 mb-4">
                        <EventCard />
                        <EventCard />
                        <EventCard />
                    </div>
                </Layout>
            )
        }
    }
}

export default Home
