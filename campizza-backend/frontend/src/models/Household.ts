type Household = {
  uid: string;
  guardian1Email: string;
  guardian1FirstName: string;
  guardian1LastName: string;
  guardian1Phone: string;
  guardian2Email: string;
  guardian2FirstName: string;
  guardian2LastName: string;
  guardian2Phone: string;
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  postalCode: string;
  emergency1FirstName: string;
  emergency1LastName: string;
  emergency1Relationship: string;
  emergency1Phone: string;
  emergency1AuthPickUp: boolean;
  emergency2FirstName: string;
  emergency2LastName: string;
  emergency2Relationship: string;
  emergency2Phone: string;
  emergency2AuthPickUp: boolean;
};

export default Household;
