import React from "react";
import AdminService from './services/admin-service';
import { Button, DropdownButton, Dropdown } from "react-bootstrap";


interface AttendanceProps {
}

export const Attendance: React.FC<AttendanceProps> = () => {

    React.useEffect(() => {
        AdminService.getAllCounselors().then(response => {
            if (response.status===200){
                let counselors = response.data;
                if (counselors.length > 0) {
                    let table = document.getElementById("attendanceTable") as HTMLTableElement;
                    console.log(counselors.length)
                    for (let c of counselors) {
                        let row = table.insertRow(2);
                        row.insertCell(0).innerHTML = c.firstName + " " + c.lastName;
                        for (let col = 1; col < 6; col++) {
                            row.insertCell(col).innerHTML = "-";
                        }
                    }
                }
            }
            else if (response.status===400){
                return false;
            }
        });
    }, [])

    // const renderCell = () = {
    //     return <div> <p>-</p> </div>;
    // }

    
    return (
        <body>
            <br/><br/><br/><br/><br/><br/>
            <div style={{marginLeft: "2.5%"}}>
                <Button variant="primary" className="backButton" href="/#/admin"> Back to Dashboard </Button>
            </div>
            <div style={{textAlign: "center"}}>
                <h1>Counselor Attendance Report</h1>
                <DropdownButton title={"Select Term"}>
                    <Dropdown.Item onClick={() => {}}>
                        Summer 2019
                    </Dropdown.Item>
                </DropdownButton>
                <br/>
                <DropdownButton title={"Select Weeks"}>
                    <Dropdown.Item onClick={() => {}}>
                        Week 2
                    </Dropdown.Item>
                </DropdownButton>
            </div>
            <div style={{marginLeft: "10%"}}>
                <p> <span style={{color: "#3E9724"}}>P </span> = Present</p>
                <p> <span style={{color: "#C80000"}}>A </span> = Absent</p>
            </div>
            
            <div style={{marginLeft: "20%", marginRight: "20%"}}>
                <table id="attendanceTable">
                    <tr>
                        <td style={{fontWeight: "bold"}}>Name</td>
                        <td style={{fontWeight: "bold"}}>M</td>
                        <td style={{fontWeight: "bold"}}>T</td>
                        <td style={{fontWeight: "bold"}}>W</td>
                        <td style={{fontWeight: "bold"}}>Th</td>
                        <td style={{fontWeight: "bold"}}>F</td>
                    </tr>
                    <tr style={{backgroundColor: "#F2F2F2"}}>
                        <td></td>
                        <td>7/1</td>
                        <td>7/2</td>
                        <td>7/3</td>
                        <td>7/4</td>
                        <td>7/5</td>
                    </tr>
                </table>
            </div>   
        </body>
    );
}

export default Attendance;