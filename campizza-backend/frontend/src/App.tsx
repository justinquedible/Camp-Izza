// Contains the url path for each file

import "bootstrap/dist/css/bootstrap.min.css";
import "./Footer.css";
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getFirestore } from "firebase/firestore";
import { HashRouter, Route, Switch } from "react-router-dom";
import NavBar from "./NavBar";
import Footer from "./Footer";
import Login from "./Login";
import Signup from "./Signup";
import UpdatePassword from "./UpdatePassword";
import ResetPassword from "./ResetPassword";
import Admin from "./Admin";
import Parent from "./Parent";
import Sessions from "./Sessions";
import Groups from "./Groups";
import AdminAttendance from "./AdminAttendance";
import AttendanceReport from "./AttendanceReport";
import BonusCamper from "./BonusCamper";
import SignUpCounselor from "./SignupCounselor";
import Pending from "./Pending";
import Household from "./HouseholdForm";
import ManageCounselors from "./ManageCounselors";
import FinalCamperForm from "./FinalCamperForm";
import Profile from "./Profile";
import Checkout from "./Checkout";
import ResetConfirmation from "./ResetConfirmation";
import NewScheduling from "./NewScheduling";
import Roster from "./Roster";
import { NewManageCampers } from "./NewManageCampers";
import Counselor from "./Counselor";
import CounselorAttendance from "./CounselorAttendance";
import EmergencyForm from "./EmergencyForm";
import AdminProtected from "./AdminProtected";
import AdminCamperForm from "./AdminCamperForm";
import CompletedTransaction from "./CompletedTransaction";
import CamperInfo from "./CamperInfo";
import CounselorInfo from "./CounselorInfo";
import CounselorForm from "./CounselorForm";

const firebaseConfig = {
  apiKey: "AIzaSyAYDy1WwuApG6seBvp0KNWHvx47-nsU4Ag",
  authDomain: "campizza-v2.firebaseapp.com",
  projectId: "campizza-v2",
  storageBucket: "campizza-v2.appspot.com",
  messagingSenderId: "39320171439",
  appId: "1:39320171439:web:7ab3ce5e904ef1bbb1f55a",
  measurementId: "G-Z9NK77XE4P",
};

export const app = initializeApp(firebaseConfig);
export const db = getFirestore();
export const analytics = getAnalytics(app);

function App() {
  return (
    <div className="App">
      <NavBar />
      <HashRouter>
        <Switch>
          <Route exact path={"/"} component={Login} />
          <Route exact path={"/home"} component={Login} />
          <Route exact path={"/login"} component={Login} />
          <Route exact path={"/signup"} component={Signup} />
          <Route exact path={"/resetPassword"} component={ResetPassword} />
          <Route exact path={"/resetConfirmation"} component={ResetConfirmation} />
          <Route exact path={"/updatePassword"} component={UpdatePassword} />
          <Route exact path={"/admin"} component={Admin} />
          <Route exact path={"/admin/managecampers"} component={NewManageCampers} />
          <Route exact path={"/admin/sessions"} component={Sessions} />
          <Route exact path={"/admin/groups"} component={Groups} />
          <Route exact path={"/admin/attendance"} component={AdminAttendance} />
          <Route exact path={"/admin/attendance/bonuscamper"} component={BonusCamper} />
          <Route exact path={"/admin/AttendanceReport"} component={AttendanceReport} />
          <Route exact path={"/admin/roster"} component={Roster} />
          <Route exact path={"/admin/managecounselors"} component={ManageCounselors} />
          <Route exact path={"/admin/AdminCamperForm"} component={AdminCamperForm} />
          <Route exact path={"/admin/emergencyform"} component={EmergencyForm} />
          <Route exact path={"/signupCounselor"} component={SignUpCounselor} />
          <Route exact path={"/counselor/pending"} component={Pending} />
          <Route exact path={"/parent"} component={Parent} />
          <Route exact path={"/HouseholdForm"} component={Household} />
          <Route exact path={"/CamperInfo"} component={CamperInfo} />
          <Route exact path={"/CamperForm"} component={FinalCamperForm} />
          <Route exact path={"/Profile"} component={Profile} />
          <Route exact path={"/NewScheduling"} component={NewScheduling} />
          <Route exact path={"/Checkout"} component={Checkout} />
          <Route exact path={"/CompletedTransaction"} component={CompletedTransaction} />
          <Route exact path={"/counselor"} component={Counselor} />
          <Route exact path={"/counselorInfo"} component={CounselorInfo} />
          <Route exact path={"/counselorForm"} component={CounselorForm} />
          <Route exact path={"/counselor/attendance"} component={CounselorAttendance} />
        </Switch>
      </HashRouter>
      <Footer />
    </div>
  );
}

export default App;
