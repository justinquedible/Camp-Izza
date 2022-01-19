// Page for parents to view their children that they registered to be campers

import "./Dashboard.css";
import React from "react";
import {
  ToggleButtonGroup,
  ToggleButton,
  ButtonGroup,
  Button,
  CardDeck,
  Card,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { getAuth } from "firebase/auth";
import { getParent, Parent } from "./models/Parent";

// import {Router, Switch, Route} from "react-router-dom";

// import NavBarInstance from './NavBar';
// import FooterInstance from './Footer';
// import campersIcon from './AdminIcons/campers-icon.png';
// import homeIcon from './AdminIcons/home-icon.png';
import Campers from "./components/Campers";
// import AuthService from "./services/auth-service";

export default function ParentDashboard() {
  // const currentUser = AuthService.currentUser();
  // console.log(currentUser);

  const [parent, setParent] = React.useState<Parent | null>(null);
  const [disableAddCamper, setDisableAddCamper] = React.useState(true);
  const auth = getAuth();

  React.useEffect(() => {
    const unsubscribe = auth.onAuthStateChanged((user) => {
      if (user) {
        // console.log(user);
        getParent(user.uid).then((parent) => {
          console.log(parent);
          setParent(parent);
        });
      }
    });
    return unsubscribe;
  }, [auth]);

  const handleCamperForm = async (e: { preventDefault: () => void }) => {
    // const currentUser = AuthService.currentUser();
    // localStorage.setItem("currentChild", "");
    // window.location.href = "#/CamperForm";
  };

  return (
    <div className="Parent">
      <br />
      <Container className="Admin-Buttons">
        <Button variant="primary" className="householdProfileButton" href="/#/householdform">
          Household Profile
        </Button>
        <br />
        <br />
        <br />
        <h3> Parent Dashboard </h3>
        <div className="row">
          <div className="col text-center">
            <br />
            My's Campers
            <br />
            <br />
            <Campers />
            <br />
            <Button
              variant="outline-success"
              className="Add-Camper"
              onClick={handleCamperForm}
              disabled={!!!parent?.household}
            >
              + Add New Camper
            </Button>
            {!!!parent?.household && <p>You must fill out the household form to add a camper</p>}
          </div>
        </div>
      </Container>
    </div>
  );
}
