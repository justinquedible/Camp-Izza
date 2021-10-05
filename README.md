**Camp Izza for our Senior Design Project** \
Some of the changes I’m hoping you can implement within the Camp Izza system are:
    _Integrate an attendance system that can be accessed by admin and counselors alike. \
    Generate groups based on registered campers with options for customization. \
    Add a counselor dashboard for Admin to interact with and manage relevant Staffing information._


## Available React Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

### Available Spring-Boot Scripts

In the project directory, you can run:

### `mvn clean install`
This will clean the target(clearing out old compiled files) and then build the project based on the POM file.
Run this command when you want to build up everything from scratch (if you removed a resource or something like that). 

### `mvn install`
Build the project based on the POM file.

### `mvn spring-boot:run -X`
Starts running spring-boot application with verbose console output. 
I like to include the -X flag because it showed me logging information that helped me debug and fix a lot of config errors.

Open http://localhost:8080 to send your GET and POST requests

## Tips for Sending Requests Between Cloud SQL and local spring-boot
- There are many connectivity options to choose from:\
https://cloud.google.com/sql/docs/mysql/connect-overview
- Make sure the client has proper authorization of at least Cloud SQL Editor
- When sending requests, you can use curl through cmd\
Ex. `curl -X POST http://localhost:8080/someAPI -d "camper=camper1&pass=pass"`
- You can also install a tool such as Postman that is an environment to send http requests from
    -  I prefer to use Postman because it's easy to navigate and edit request in a saved environment

## Tips for deploying to Google App Engine
- Install and Configure Google Cloud Code or Run mvn appengine:deploy
- When deploying ensure that no global variables are being passed to JAR 
    -  The jar will automatically connect to CloudSql while in app engine
    - Be cautious of setUpProxy when testing React locally