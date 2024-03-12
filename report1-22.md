# Part Ⅰ. Project Proposal

### Project Overview

**Target users：**

- SUSTech students

**Functionalities：**

- Mobile-friendly class schedule access
- Inquire all the curriculum information
- Course discussion platform
- AI-driven course selection assistance
- Second-hand textbooks and items trading platform 

**Expected outcome：**

- To provide students with convenient course management, study interaction, and a platform for trading second-hand textbooks and items, ultimately enhancing the overall academic experience.

  

### Preliminary Requirement Analysis

 **Functional Requirements:**

1. **Mobile-friendly Class Schedule Access:**
  - Users can conveniently view and access their class schedules on various mobile devices.
  - The system must provide an intuitive and user-friendly interface for easy navigation and schedule management.
2. **University-wide Course Information:**
  - The system should allow users to search and retrieve detailed information about all university courses.
  - Course information should include descriptions, schedules, and instructor details.
3. **Course Discussion Platform:**
  - Users should be able to post comments, share experiences, and engage in discussions related to specific courses.
  - The platform should support user interactions, including replies and likes.
4. **AI Assistance:**
  - The AI system will analyze user course schedules, offering personalized recommendations for elective courses.
  - Users can interact with the AI for advice on course selection and general learning support.
5. **Second-hand Textbooks and Items Trading Platform:**
  - Students can post and browse information about second-hand textbooks and items on the platform.
  - The trading platform supports buying, selling, or exchanging textbooks and items among students.
6. **Schedule Planning:**
  - Users should be able to plan and organize their weekly routines using the scheduling feature.
  - The scheduling feature supports easy editing, adding, and deleting of events.

**Non-functional Requirements:**

1. **Usability:**
  - The user interface should be intuitive, ensuring a positive and seamless user experience.
  - Clear instructions and guidance should be provided for users.
2. **Security:**
  - User data, especially personal and academic information, should be securely stored and transmitted.
  - The trading platform data should be adequately protected, including the security of payment and personal information.
3. **Performance:**
  - The system should demonstrate efficient performance with quick response times.
  - It should handle concurrent users without significant performance degradation.

**Data Requirements:**

1. **University-wide Course Information:**

 - **Data Required:** Comprehensive course details for all university courses.

 - **Data Retrieval:** Web scraping from the university's academic system.

2. **User Personal Course Information:**

 - **Data Required:** Individualized course details for each user.

 - **Data Retrieval:** Sending HTTP requests to the academic system.

3. **Second-hand Trading Goods Information:**

 - **Data Required:** Information about second-hand goods uploaded by users.

 - **Data Retrieval:** User uploads on the platform.

4. **Course Evaluation Information:**

 - **Data Required:** User-contributed evaluations and comments for various courses.

 - **Data Retrieval:** User submissions on the platform.

**Technical requirements **

1. **Operating Environment:**
  - The system should be accessible through various mobile devices and mainstream web browsers.
  - Compatibility with major operating systems (iOS, Android) is essential.
2. **Tech Stack:**
  - Front-end: HTML, CSS , JavaScript, vuetify, Echarts
  - Back-end: SpringBoot, Mybatis-plus, SpringCache, MySQL, Caffeine, SpringSecurity + JWT

# Part Ⅱ. Task Decomposition & Planning

![Board](snapshots\Board.png)
![Roadmap](snapshots\Roadmap.png)

# Part Ⅲ. AI Usage

1. No. As we have completed the proposal of this project by ourselves earlier, we didn't ask ChatGPT to generate the propose features. But we tried to provide the decided features to it, and ask it to give advice on the exsiting features, the response is plain and not very valuable.
2. Yes, we give ChatGPT a overview of our project and let it help to summarize it into the format of the "preliminary requirement analysis ", the result is not clear enough in data requirements and Functional requirements, we gave more details to ChatGPT on both two points, it generated clearer answer, and we made some simple modifications to his response to make it more specific and clear, resulting in our final version.
3. Yes, we told ChatGPT the funtionailities, target users and expected outcome of our project, and ask it to generate the user stories from the information we provide. As we explained our project very detailedly, ChatGPT responded many useful user strories, and we select some of the reasonable user stories as our project's user stories.
4. Yes, we feed the user stories that ChatGPT generated before back to itself, and ask it to extract some issues and tasks from the user stories. It did a good job and gave us many useful advice about how to decompose the task.