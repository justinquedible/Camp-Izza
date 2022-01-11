// Page for counselors to view their dashboard

import React from 'react';
import {Button, Card, Container} from "react-bootstrap";
import './Dashboard.css';
import AuthService from "./services/auth-service";
import CamperService from "./services/camper-service";

interface Props {
}
interface Medical{
    id: number;
    allergies: string;
    allergy_names: string;
    medicationNames: string;
    comments: string;
    activitiesName:string;
    illnesses_names: string
}

interface ICampers {
    firstName: string;
    group: string;
    lastName: string;
    id: number;
    medicalRecord: Medical;
    comment: string;

}


const camperArray:ICampers[] = [];

export const Counselor: React.FC<Props> = () => {
    const [list1, setList1] = React.useState(camperArray);// Used for group 1
    let group = AuthService.currentUser().group;
    React.useEffect(() => {
        CamperService.getGroupInfo(group).then(response => {

            setList1(response.data);

        })
    }, []);

    return (
        <body>
        <Container className="Admin-Buttons">
            <br /><br />
            <h3> Counselor Dashboard </h3>

            <div className="center"><Button variant="primary" href="/#/counselorInfo"> Profile </Button></div>
            <div className="center"><Button variant="primary" href="/#/counselor/attendance"> Take Attendance </Button></div><br /><br />


            <div className={"overflowTable"}>
            <h5> Your Group: {group.substring(0, 1).toUpperCase()}{group.substring(1, group.length - 1)} {group.substring(group.length - 1)}</h5>
            <div className="overflowTable">
                <table className={"manageTable"}>
                    <tr>
                        <td> Name </td>
                        <td> Conditions </td>
                        <td> Allergies/Dietary Restrictions </td>
                        <td> Medications </td>
                        <td> Activity Restrictions </td>
                        <td> Notes </td>
                    </tr>
                    {list1.map(item => (
                        <tr>
                            <td> {item.firstName} {item.lastName} </td>
                            <td> {item.medicalRecord.illnesses_names} </td>
                            <td> {item.medicalRecord.allergy_names} </td>
                            <td> {item.medicalRecord.medicationNames} </td>
                            <td> {item.medicalRecord.activitiesName} </td>
                            <td> {item.comment} </td>

                        </tr>
                    ))}
                </table>
                </div>

            </div>
        </Container>
        </body>
    )

}

export default Counselor;
