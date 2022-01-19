import { db } from "../App";
import { doc, getDoc, setDoc } from "firebase/firestore";
import { Household } from "./Household";

type Parent = {
  uid: string;
  email: string;
  household?: Household;
};

const addParent = async (parent: Parent) => {
  const { uid, ...parentWihtoutUid } = parent;
  await setDoc(doc(db, "parents", uid), parentWihtoutUid);
  await setDoc(doc(db, "userRoles", uid), { role: "parent" });
};

const getParent = async (uid: string) => {
  const docSnap = await getDoc(doc(db, "parents", uid));
  if (docSnap.exists()) {
    return { uid, ...docSnap.data() } as Parent;
  }
  return null;
};

export type { Parent };
export { addParent, getParent };
