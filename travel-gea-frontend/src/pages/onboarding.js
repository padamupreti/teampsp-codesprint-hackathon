import BlankLayout from "@/components/BlankLayout"
import Link from "next/link"

function Onboarding() {
    return (
        <BlankLayout>
            <div className="grid place-items-center h-screen">
                <div className="-translate-y-1/2">
                    <div className="font-bold text-5xl text-center mb-8">
                        You are a
                    </div>
                    <div className="flex gap-2">
                        <Link
                            href={"/tourist-sign-up"}
                            className="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
                        >
                            Tourist
                        </Link>
                        <Link
                            href={"/guide-sign-up"}
                            className="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
                        >
                            Guide
                        </Link>
                        <Link
                            href={"#"}
                            className="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
                        >
                            Partner Organization
                        </Link>
                    </div>
                </div>
            </div>
        </BlankLayout>
    )
}

export default Onboarding
