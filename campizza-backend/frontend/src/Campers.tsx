import * as React from 'react'


import axios from "axios";
import {Card, CardColumns} from "react-bootstrap";
import AuthService from "./services/auth-service";
import { Button } from 'react-bootstrap';
import CamperService from "./services/camper-service";

const API_URL = "http://localhost:8080/api/campers/";
//const API_URL = "https://rugged-sunbeam-229808.uc.r.appspot.com/api/campers/";
interface ICamper {
    id: string;
    firstName: string,
    lastName: string
}

const defaultCampers:ICamper[] = [];

const Campers: React.FC = () => {
    const currentUserID = AuthService.currentUser().id;

    const [data, setData]: [ICamper[], (campers: ICamper[]) => void] = React.useState(defaultCampers);

    React.useEffect(() => {
        axios.get<ICamper[]>(API_URL + currentUserID).then(response => {
            setData(response.data);


        })
            .catch((error) => {
                if (error.response.status === 500) {
                    window.alert("500 Internal Server Error: Please try again later");
                    // throw new Error("500 Internal Server Error: Please try again later");

                }
            });
    }, []);

    function shandleCamperClick(name:string, id:string) {
        localStorage.setItem("currentChild", name)
        localStorage.setItem("currentChildID", id)

    }

    const handleCamperClick = async (e: { preventDefault: () => void; }) => {
        //localStorage.setItem("currentChild", ICamper.firstName)

    };


    return (
        <CardColumns>
            {data.map(item => (

                <Card border="success" style={{width: '80%'}}>
                    <Card.Body>
                        <Card.Title>{item.firstName}</Card.Title>
                        <Card.Link  onClick={() => shandleCamperClick(item.firstName, item.id)} href= "#/CamperForm">
                            📝 Info
                        </Card.Link >
                        <br />
                        <Card.Link  onClick={() => shandleCamperClick(item.firstName, item.id)} href="/#/newScheduling">
                            📅 Scheduling
                        </Card.Link>
                    </Card.Body>
                </Card>
            ))}

        </CardColumns>
    )
}

export default Campers;
