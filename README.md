# HoliBuddy Vacation Builder App
## Purpose
The purpose of the HoliBuddy application is to provide a comprehensive and
user-friendly tool for travelers to efficiently plan and manage their vacations and 
associated excursions. This application is designed to cater to the needs of individuals 
who seek an organized and hassle-free way to track their vacation plans, including details 
such as accommodation, dates, and specific activities or excursions planned for each trip. 
By centralizing all this information in one accessible mobile app, users can easily add, update, 
and view their vacation plans at a glance, ensuring that every aspect of their trip is 
well-organized and easily retrievable. The app's intuitive interface allows for simple 
navigation, making the process of vacation planning less daunting and more enjoyable.

Moreover, the application's integration with the Room Framework and local SQLite database ensures 
a reliable and secure way to store vacation data. This not only enhances the user experience by 
providing a seamless and responsive interface but also ensures data integrity and privacy. 
The app's capabilities extend beyond mere scheduling; it includes features such as detailed 
views of each vacation and excursion, alerts for important dates, and the ability to share 
plans through various channels like email, SMS, or clipboard. These functionalities are 
particularly beneficial for users who need to coordinate plans with fellow travelers or 
keep family and friends informed. By offering a blend of organizational tools, reminders, 
and sharing options, the vacation scheduler app stands out as an indispensable tool for 
any traveler seeking an efficient and streamlined way to plan and enjoy their vacations.

## TASK C - USER APPLICATION INSTRUCTIONS
The HoliBuddy App is designed to be user-friendly and intuitive. Here are the instructions on how to
operate the application to utilize all its features effectively:

### 1. Home Screen:
    - Upon launching the app, you will be greeted with the Home Screen. This screen serves as your 
      navigation hub to access different features of the app.
    - From here, you can navigate to view your vacation list which allows you to also navigate to 
      excursion list, or add new vacations and excursions.

### 2. Adding a Vacation:
    - To add a new vacation, navigate to the 'Floating Add button' section from the Vacation List 
      screen.
    - Fill in the details such as vacation title, lodging place, start date, and end date.
    - Ensure the dates are entered in the correct format and that the end date is after the start 
      date.
    - Once all details are filled in, save the vacation. It will now appear in your vacation list.

### 3. Viewing and Editing Vacations:
    - Access your list of vacations from the Home Screen.
    - Select a vacation to view its details. Here, you can also edit the information or delete the 
      vacation.
    - Remember, a vacation cannot be deleted if there are excursions associated with it.

### 4. Searching through Vacations
    - Use the SearchView finder.
    - Type in the vacation you're looking for. 
    - Select Item you are searching for.

### 5. Setting Alerts for Vacations:
    - While adding or editing a vacation, you can set alerts for the start and end dates.
    - These alerts will notify you when the vacation is about to start or end.

### 6. Sharing Vacation Details:
    - In the detailed view of a vacation, use the sharing feature to share details via email, 
      clipboard, or SMS.
    - The sharing feature automatically populates with the vacation details for easy sharing.

### 7. Generating Report:
    - Use the 'Generate report to excell...' in the menu to export to excel
    - After selection, appplicaiton will bring you to you android documents
    - Select csv file name and then save
    - Review vacation reports as needed

### 7. Adding Adventures:
    - Navigate to the ‘Floating Add button’ section from within a vacation's detailed view.
    - Enter details like the excursion title and date, ensuring the date falls within the vacation's
      start and end dates.
    - Save the excursion, and it will be listed under the associated vacation.

### 8. Viewing and Editing Adventures:
    - Access the list of excursions from a vacation's detailed view.
    - Select an excursion to view its details, where you can also edit or delete the information.
    - Use Google Search Adventures to get ideas for additional adventures.

### 9. Setting Alerts for Adventures:
    - While adding or editing an excursion, you can set an alert for the excursion date.
    - This alert will notify you about the excursion on the specified date.

### 10. Generating APK:
    - For developers or testers, generate a signed APK from Android Studio to test the application 
      on different devices.

### 11. Navigating the App:
    - Use the navigation bar or back button to move between different screens and functionalities.

### 12. Exit or Close the App:
    - You can exit the application normally through your device's home or back button.

Remember to regularly update the application and check for new features or enhancements. 
The app is compatible with Android 8.0 and higher, ensuring a wide range of device compatibility.
Additionally, the application runs in both portrait and landscape mode for scalability.

## Setup for Maintenance Instructions (TASK C)

## Setting Up the Development Environment:

### 1. Install Android Studio:
    - Download and install the latest version of [Android Studio](https://developer.android.com/studio).
    - Ensure you have the Java Development Kit (JDK) installed, as it's required for Android 
      development.

### 2. Clone the Repository:
    - Use Git to clone the repository of your app to your local machine. 
      In Android Studio, go to `File` > `New` > `Project from Version Control` and enter your 
      repository URL.

### 3. Install Required SDKs and Tools:
    - Open the project in Android Studio. The IDE should automatically detect if any Android SDKs  
      or tools are missing and prompt you to install them.

### 4. Dependencies and Libraries:
    - Ensure all dependencies in your `build.gradle` file are up to date. Android Studio should 
      prompt you if any updates are available.

## Running and Testing the Application:

### 1. Build the Project:
    - Build your project by clicking `Build` > `Make Project`. Resolve any compilation issues that 
    arise.

### 2. Emulator or Physical Device:
    - You can run the app on an Android emulator or a physical device. To set up an emulator, 
      go to `Tools` > `AVD Manager`and create a new Android Virtual Device that matches the 
      specifications needed for your app.
    - To use a physical device, enable USB debugging on the device and connect it to your computer 
      via USB.

### 3. Run the Application:
    - Click the `Run` button in Android Studio and select the target device (emulator or connected 
      physical device).

### 4. Testing:
    - Perform functional testing by navigating through the app's features, focusing on vacation and 
      excursion creation, updates, deletions, and alert functionalities.
    - Use the Android Studio debugger and logcat for troubleshooting and identifying issues.

## Maintenance Tasks:

### 1. Updating Features and Fixing Bugs:
    - Regularly update features based on user feedback. Prioritize bug fixes, especially those 
      related to core functionalities like date handling and notifications.

### 2. Performance Optimization:
    - Use Android Studio's profiling tools to identify performance bottlenecks and optimize them.

### 3. Security Updates:
    - Keep security measures up to date, including encrypting sensitive data and ensuring secure   
      data transmission.

### 4. Keeping Dependencies Updated:
    - Regularly update the libraries and dependencies to their latest versions for improved 
      performance and security.

### 5. Version Control:
    - Use Git for version control. Regularly commit changes with descriptive messages and use 
      branches for developing new features. 

### 6. Documentation:
    - Update the in-code documentation and external documentation files to reflect changes and 
      provide clarity on complex areas of the code.

## Preparing for Deployment:

### 1. Final Testing:
    - Conduct thorough testing, including unit testing, integration testing, and user acceptance 
      testing.

### 2. Generate Signed APK:
    - For deployment, generate a signed APK. Go to `Build` > `Generate Signed Bundle / APK` and 
      follow the prompts.

### 3. Update Release Notes:
    - Prepare release notes for the new version, detailing new features, bug fixes, and any other 
      relevant information.
## Version Number
Use link to view current version number
>https://gitlab.com/wgu-gitlab-environment/student-repos/mmcm108/d424-software-engineering-capstone/-/blob/working_branch/build.gradle.kts

## SIGNED APK VERSION
VERSION: 
>31
## URL TO REPOSITORY
URL
>https://gitlab.com/wgu-gitlab-environment/student-repos/mmcm108/d424-software-engineering-capstone/-/tree/working_branch?ref_type=heads

# Author
>Mike McMillin