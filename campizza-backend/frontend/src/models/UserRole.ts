import { db } from "../App";
import { getDoc, doc } from "firebase/firestore";

type UserRole = {
  uid: string;
  role: "parent" | "counselor" | "admin";
};

const getUserRole = async (uid: string) => {
  const docSnap = await getDoc(doc(db, "userRoles", uid));
  if (docSnap.exists()) {
    const user = docSnap.data() as UserRole;
    return user.role;
  }
  return null;
};

export type { UserRole };
export { getUserRole };
