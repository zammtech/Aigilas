package com.spx.text;import java.util.*;import com.spx.core.*;import com.spx.text.*;import com.xna.wrapper.*;
    public class ActionTextHandler
    {
        private ActionText[] defaultPool = new ActionText[100];
        private int defaultIndex = 0;

        private List<Text> _contents = new ArrayList<Text>();

        public ActionTextHandler()
        {
            for (int ii = 0; ii < defaultPool.length; ii++)
            {
                defaultPool[ii] = new ActionText();
            }
        }

        public void WriteAction(String contents, int lifespan, int x, int y)
        {
            defaultPool[defaultIndex].Reset(contents,lifespan,x, y);
            Add(defaultPool[defaultIndex]);
            defaultIndex = (defaultIndex + 1) % defaultPool.length;
        }

        public void Add(Text textToAdd)
        {
            if (!_contents.contains(textToAdd))
            {
                _contents.add(textToAdd);
                TextManager.Add(textToAdd);
            }
        }

        public void Clear()
        {
            _contents.clear();
        }

        public void Update()
        {
            for (int ii = 0; ii < _contents.size(); ii++)
            {
                if (_contents.get(ii).Update() <= 0)
                {
                    _contents.remove(_contents.get(ii));
                    ii--;
                }
            }
        }

        public void Draw()
        {

            if (XnaManager.Renderer != null)
            {
                for(Text component: _contents)
                {
                    component.Draw();
                }
            }
        }
    }
