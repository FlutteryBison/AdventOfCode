
namespace list
{

    class StringList : IComparable<StringList>
    {
        private readonly string list;


        public StringList(string list, bool convertAllElemntsToSingleton)
        {
            if (!isValid(list))
            {
                throw new Exception("String is not a valid list");
            }

            if (convertAllElemntsToSingleton)
            {
                list = convertAllToList(list);
            }

            this.list = list;





        }

        public StringList(string list, bool convertAllElemntsToSingleton, char[] validElems)
        {

            if (!isValid(list))
            {
                throw new Exception("String is not a valid list");
            }

            //check additional constraints
            foreach (char c in list)
            {
                if (c != ',' && c != '[' && c != ']' && !validElems.Contains(c))
                {
                    throw new Exception("List contains elements not in valid elems");
                }
            }

            if (convertAllElemntsToSingleton)
            {
                list = convertAllToList(list);
            }

            this.list = list;

        }


        private bool isValid(string list)
        {
            //check if is valid

            //check if is list
            if (!(list.Length > 0 && list.StartsWith('[') && list.EndsWith(']')))
            {
                return false;
            }


            int depth = 0;
            for (int i = 0; i < list.Length; i++)
            {

                if (list[i] == '[')
                {
                    depth++;
                }
                else if (list[i] == ']')
                {
                    depth--;
                    if (depth < 0)
                    {
                        return false;
                    }
                }


            }

            return depth == 0;

        }


        //converts all non list elements to singleton lists
        private static string convertAllToList(string str)
        {
            string outStr = "";
            int idx = 0;
            while (idx < str.Length)
            {
                //check is digit
                if (str[idx] >= '0' && str[idx] <= '9')
                {
                    //the element may be more that one digit long
                    //run along the element untill reaching the end
                    //Store the element string to avoid having to backtrack
                    string elem = "";
                    int elemStartidx = idx;
                    while (str[idx] >= '0' && str[idx] <= '9')
                    {
                        elem += str[idx];
                        idx++;
                    }

                    //check if elements is in a singleton list
                    if (!(str[elemStartidx - 1] == '[' && str[idx] == ']'))
                    {
                        outStr += "[" + elem + "]";
                    }
                    else
                    {
                        outStr += elem;
                    }


                }
                else
                {
                    outStr += str[idx];
                    idx++;
                }
            }

            return outStr;
        }




        //only works if all non list elements have been converted to singleton lists
        public string getElementAt(int index)
        {
            int curElem = 0;
            int setDepth = 0;
            string strright = list;

            int i = 0;
            while (i < strright.Length)
            {
                if (strright[i] == '[')
                {
                    setDepth++;
                }
                if (strright[i] == ']')
                {
                    setDepth--;
                }
                if (strright[i] == ',' && setDepth == 1)
                {
                    if (curElem == index)
                    {
                        return strright[1..i];
                    }

                    //remove left element and
                    strright = "[" + strright[(i + 1)..];

                    i = -1;
                    setDepth = 0;
                    curElem++;

                }
                i++;



            }

            //if it is the last element and index looked for match return the rught str. It wasnt returned before as there was no comma after it
            if (curElem == index)
            {
                return strright[1..(strright.Length - 1)];
            }

            return null; ;
        }


        public override string ToString()
        {
            return list;
        }

        public int CompareTo(StringList other)
        {

            int compRes = 0;

            int i = 0;
            while (compRes == 0)
            {


                string elem1 = getElementAt(i);
                bool iss1Int = int.TryParse(elem1, out int res1);



                string elem2 = other.getElementAt(i);
                bool iss2Int = int.TryParse(elem2, out int res2);

                //if both are integers compare
                if (iss1Int && iss2Int)
                {
                    if (res1 < res2)
                    {
                        return -1;
                    }
                    if (res1 == res2)
                    {
                        return 0;
                    }
                    return 1;
                }

                //if one is null, the list is empty so result can be determined
                if ((elem1 == "" || elem1 == null) && elem2 != null)
                {
                    return -1;
                }
                if (elem1 != null && (elem2 == null || elem2 == ""))
                {
                    return 1;
                }
                if ((elem1 == null || elem1 == "") && (elem2 == null || elem2 == ""))
                {
                    return 0;
                }


                //both are not integers, If one is an integer convert it to a list
                if (iss1Int)
                {
                    elem1 = '[' + elem1 + ']';
                }
                //both are not integers, If one is an integer convert it to a list
                if (iss2Int)
                {
                    elem2 = '[' + elem2 + ']';
                }

                compRes = new StringList(elem1, false).CompareTo(new StringList(elem2, false));

                i++;
            }

            return compRes;
        }
    }

}