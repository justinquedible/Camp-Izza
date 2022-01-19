// Page for parents to view and edit their household information

import "./HouseholdForm.css";
import "intl-tel-input/build/css/intlTelInput.css";
import React from "react";
import { Button, Container, Form, Col, Row } from "react-bootstrap";
import { getAuth } from "firebase/auth";
import intlTelInput from "intl-tel-input";
import { Parent, getParent } from "./models/Parent";

// import { Router, Switch, Route } from "react-router-dom";

// import NavBarInstance from "./NavBar";
// import FooterInstance from "./Footer";
// import axios from "axios";
// import currentUser from "./services/auth-service";
// import authHeader from "./services/auth-header";
// import ParentService from "./services/parent-service";
// import AuthService from "./services/auth-service";

export default function HouseholdForm() {
  const [parent, setParent] = React.useState<Parent | null>(null);
  const auth = getAuth();

  // Initialize international telephjone input plugin
  const input1 = React.useRef<HTMLInputElement>(null);
  const input2 = React.useRef<HTMLInputElement>(null);
  const input3 = React.useRef<HTMLInputElement>(null);
  const input4 = React.useRef<HTMLInputElement>(null);
  const [telInputs, setTelInputs] = React.useState<intlTelInput.Plugin[]>([]);

  React.useEffect(() => {
    if (input1.current && input2.current && input3.current && input4.current) {
      const inputs = [input1.current, input2.current, input3.current, input4.current];
      // const telInputs = [telInput1, telInput2, telInput3, telInput4];
      const telInputs = [];
      for (let i = 0; i < inputs.length; i++) {
        telInputs.push(
          intlTelInput(inputs[i], {
            preferredCountries: ["us"],
            separateDialCode: true,
            utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.15/js/utils.min.js",
          })
        );
      }
      setTelInputs(telInputs);
    }
  }, []);

  // const [values, setValues] = React.useState({
  //   address1: "",
  //   address2: "",
  //   country: "",
  //   city: "",
  //   state: "",
  //   zipCode: "",
  //   guardian1FirstName: "",
  //   guardian1LastName: "",
  //   guardian1Email: "",
  //   guardian1Phone: "",
  //   guardian2FirstName: "",
  //   guardian2LastName: "",
  //   guardian2Email: "",
  //   guardian2Phone: "",
  //   emergency1FirstName: "",
  //   emergency1LastName: "",
  //   emergency1Relation: "",
  //   emergency1Phone: "",
  //   emergency1AuthPickUp: "",
  //   emergency2FirstName: "",
  //   emergency2LastName: "",
  //   emergency2Relation: "",
  //   emergency2Phone: "",
  //   emergency2AuthPickUp: "",
  // });

  const [values, setValues] = React.useState({
    address: {
      addressLine1: "",
      addressLine2: "",
      city: "",
      state: "",
      zipCode: "",
      country: "",
    },
    guardian1: {
      email: "",
      firstName: "",
      lastName: "",
      phone: "",
    },
    guardian2: {
      email: "",
      firstName: "",
      lastName: "",
      phone: "",
    },
    emergencyContact1: {
      firstName: "",
      lastName: "",
      relation: "",
      phone: "",
      authPickUp: false,
    },
    emergencyContact2: {
      firstName: "",
      lastName: "",
      relation: "",
      phone: "",
      authPickUp: false,
    },
  });

  React.useEffect(() => {
    const unsubscribe = auth.onAuthStateChanged((user) => {
      if (user) {
        // console.log(user);
        getParent(user.uid).then((parent) => {
          // console.log(parent);
          setParent(parent);
          setValues((values) => ({
            ...values,
            guardian1: { ...values.guardian1, email: parent?.email ? parent.email : "" },
          }));
        });
      }
    });
    return unsubscribe;
  }, [auth]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    // console.log(e);
    // console.log(telInput?.getNumber());
    // console.log(telInput?.getSelectedCountryData());
    // console.log(telInput?.getExtension());
    // console.log(telInput?.isValidNumber());
    // console.log(telInput);
  };

  const handleChange = (property: string, name: string) => (e: { target: { value: any } }) => {
    setValues({ ...values, [property]: { [name]: e.target.value } });
  };

  return (
    <div className={"HouseholdForm"}>
      <br />
      <Container className="Text-Form">
        <Button variant="primary" className="backButton" href="/#/parent">
          Back
        </Button>
        <h3>Household Profile</h3>
        <br />
        <p>
          <b>*</b> indicates a mandatory field.
        </p>

        <Form onSubmit={handleSubmit}>
          <h5>Primary Guardian</h5>
          <Row>
            <Form.Group as={Col} controlId="guardian1FirstName">
              <Form.Label>
                <b>* </b>First Name
              </Form.Label>
              <Form.Control required onChange={handleChange("guardian1", "firstName")} />
            </Form.Group>
            <Form.Group as={Col} controlId="guardian1LastName">
              <Form.Label>
                <b>* </b>Last Name
              </Form.Label>
              <Form.Control required />
            </Form.Group>
          </Row>
          <Row>
            <Form.Group as={Col} controlId="guardian1Email">
              <Form.Label>
                <b>* </b>Email
              </Form.Label>
              <Form.Control type="email" readOnly required defaultValue={parent?.email} />
            </Form.Group>
            <Form.Group as={Col} controlId="guardian1PhoneNumber">
              <div className="phone">
                <Row>
                  <Form.Label>
                    <b>* </b>Phone Number
                  </Form.Label>
                </Row>
                <Row>
                  <Form.Control ref={input1} type="tel" required />
                </Row>
              </div>
            </Form.Group>
          </Row>

          <br />
          <h5>Secondary Guardian</h5>
          <Row>
            <Form.Group as={Col} controlId="guardian2FirstName">
              <Form.Label>First Name</Form.Label>
              <Form.Control />
            </Form.Group>
            <Form.Group as={Col} controlId="guardian2LastName">
              <Form.Label>Last Name</Form.Label>
              <Form.Control />
            </Form.Group>
          </Row>
          <Row>
            <Form.Group as={Col} controlId="guardian2Email">
              <Form.Label>Email</Form.Label>
              <Form.Control type="email" />
            </Form.Group>
            <Form.Group as={Col} controlId="guardian2PhoneNumber">
              <div className="phone">
                <Row>
                  <Form.Label>Phone Number</Form.Label>
                </Row>
                <Row>
                  <Form.Control ref={input2} type="tel" />
                </Row>
              </div>
            </Form.Group>
          </Row>

          <br />
          <h5>Residence</h5>
          <Row>
            <Form.Group as={Col} controlId="address1">
              <Form.Label>
                <b>* </b>Address 1
              </Form.Label>
              <Form.Control required placeholder="Street address or P.O. Box" />
            </Form.Group>
            <Form.Group as={Col} controlId="address2">
              <Form.Label>Address 2</Form.Label>
              <Form.Control placeholder="Apt, suite, unit, etc." />
            </Form.Group>
          </Row>
          <Row>
            <Form.Group as={Col} controlId="city">
              <Form.Label>
                <b>* </b>City
              </Form.Label>
              <Form.Control required />
            </Form.Group>
            <Form.Group as={Col} controlId="state">
              <Form.Label>
                <b>* </b>State
              </Form.Label>
              <Form.Control required defaultValue="California" />
            </Form.Group>
            <Form.Group as={Col} controlId="postalCode">
              <Form.Label>
                <b>* </b>ZIP Code
              </Form.Label>
              <Form.Control required />
            </Form.Group>
          </Row>
          <Form.Group controlId="country">
            <Form.Label>
              <b>* </b>Country
            </Form.Label>
            <Form.Control required defaultValue="United States" />
          </Form.Group>

          <br />
          <h5>Emergency Contact 1</h5>
          <Row>
            <Form.Group as={Col} controlId="emergencyContact1FirstName">
              <Form.Label>
                <b>* </b>First Name
              </Form.Label>
              <Form.Control required />
            </Form.Group>
            <Form.Group as={Col} controlId="emergencyContact1LastName">
              <Form.Label>
                <b>* </b>Last Name
              </Form.Label>
              <Form.Control required />
            </Form.Group>
          </Row>
          <Row>
            <Form.Group as={Col} controlId="emergencyContact1Relation">
              <Form.Label>
                <b>* </b>Relation to Camper(s)
              </Form.Label>
              <Form.Control required />
            </Form.Group>
            <Form.Group as={Col} controlId="emergencyContact1Phone">
              <div className="phone">
                <Row>
                  <Form.Label>
                    <b>* </b>Phone Number
                  </Form.Label>
                </Row>
                <Row>
                  <Form.Control ref={input3} type="tel" required />
                </Row>
              </div>
            </Form.Group>
          </Row>
          <Form.Group controlId="check">
            <Row>
              <Col xs="1">
                <Form.Control className="check" type="checkbox" />
              </Col>
              <Col>
                <Form.Label>
                  <b>* </b>This person is authorized to pick up my camper(s)
                </Form.Label>
              </Col>
            </Row>
          </Form.Group>

          <br />
          <h5>Emergency Contact 2</h5>
          <Row>
            <Form.Group as={Col} controlId="emergencyContact2FirstName">
              <Form.Label>
                <b>* </b>First Name
              </Form.Label>
              <Form.Control required />
            </Form.Group>
            <Form.Group as={Col} controlId="emergencyContact2LastName">
              <Form.Label>
                <b>* </b>Last Name
              </Form.Label>
              <Form.Control required />
            </Form.Group>
          </Row>
          <Row>
            <Form.Group as={Col} controlId="emergencyContact2Relation">
              <Form.Label>
                <b>* </b>Relation to Camper(s)
              </Form.Label>
              <Form.Control required />
            </Form.Group>
            <Form.Group as={Col} controlId="emergencyContact2Phone">
              <div className="phone">
                <Row>
                  <Form.Label>
                    <b>* </b>Phone Number
                  </Form.Label>
                </Row>
                <Row>
                  <Form.Control ref={input4} type="tel" required />
                </Row>
              </div>
            </Form.Group>
          </Row>
          <Form.Group controlId="check">
            <Row>
              <Col xs="1">
                <Form.Control className="check" type="checkbox" />
              </Col>
              <Col>
                <Form.Label>
                  <b>* </b>This person is authorized to pick up my camper(s)
                </Form.Label>
              </Col>
            </Row>
          </Form.Group>

          <div className="center">
            <Button type="submit" variant="success" className="buttonTxt">
              Save
            </Button>
          </div>
        </Form>
      </Container>
    </div>
  );
}
