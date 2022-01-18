import { db } from "../App";
import { setDoc, doc } from "firebase/firestore";
import Household from "./Household";

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

export type { Parent };
export { addParent };
