function TouristSignUpForm({ handleSubmit }) {
    return (
        <form method="POST" className="mt-2" onSubmit={handleSubmit}>
            <div className="mb-5">
                <label
                    htmlFor="signup-fullname"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                    Full Name
                </label>
                <input
                    type="text"
                    name="touristName"
                    id="signup-fullname"
                    required
                    className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                />
            </div>
            <div className="mb-5">
                <label
                    htmlFor="signup-interests"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                    Choose your interests
                </label>
                <select
                    name="interests"
                    id="signup-interests"
                    multiple
                    className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                >
                    <option value="adventure">Adventure</option>
                    <option value="nature">Nature</option>
                    <option value="social-cultural">Social/Culture</option>
                    <option value="music">Music</option>
                    <option value="history">History</option>
                </select>
            </div>
            <div className="mb-5">
                <label
                    htmlFor="signup-description"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                    About you
                </label>
                <textarea
                    name="touristDescription"
                    id="signup-description"
                    required
                    className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                />
            </div>
            <div className="mb-5">
                <label
                    htmlFor="signup-email"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                    Email
                </label>
                <input
                    type="email"
                    name="email"
                    id="signup-email"
                    required
                    className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                />
            </div>
            <div className="mb-5">
                <label
                    htmlFor="signup-username"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                    Username
                </label>
                <input
                    type="text"
                    name="userName"
                    id="signup-username"
                    required
                    className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                />
            </div>
            <div className="mb-5">
                <label
                    htmlFor="signup-password"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                    Password
                </label>
                <input
                    type="password"
                    name="password"
                    id="signup-password"
                    required
                    className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                />
            </div>
            <button
                type="submit"
                className="cursor-pointer text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
            >
                Sign Up
            </button>
        </form>
    )
}

export default TouristSignUpForm
