type Household = {
  address: {
    addressLine1: string;
    addressLine2: string;
    city: string;
    state: string;
    zipCode: string;
    country: string;
  };
  guardian1: {
    email: string;
    firstName: string;
    lastName: string;
    phone: string;
  };
  guardian2: {
    email: string;
    firstName: string;
    lastName: string;
    phone: string;
  };
  emergencyContact1: {
    firstName: string;
    lastName: string;
    relation: string;
    phone: string;
    authPickUp: boolean;
  };
  emergencyContact2: {
    firstName: string;
    lastName: string;
    relation: string;
    phone: string;
    authPickUp: boolean;
  };
};

export type { Household };
