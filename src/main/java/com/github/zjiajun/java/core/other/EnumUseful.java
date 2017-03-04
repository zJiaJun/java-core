package com.github.zjiajun.java.core.other;

/**
 * @author zhujiajun
 * @since 2017/3/4
 */
public class EnumUseful {

    public static void main(String[] args) {
        ActionEnum noneActionEnum = ActionEnum.NONE;
        ActionEnum warningActionEnum = ActionEnum.WARNING;
        ActionEnum failingActionEnum = ActionEnum.FAILING;

        String className = new Object() {
            String getClassName() {
                String name = this.getClass().getName();
                return name.substring(0, name.indexOf("$"));
            }
        }.getClassName();
        noneActionEnum.doAction(className);
        warningActionEnum.doAction(className);
        failingActionEnum.doAction(className);

    }

    private enum ActionEnum {

        NONE {
            @Override
            public void doAction(String params) {
                //nothing
            }
        },

        WARNING {
            @Override
            public void doAction(String params) {
                System.out.println("Waring: " + params);
            }
        },

        FAILING {
            @Override
            public void doAction(String params) {
                throw new RuntimeException("Failing: " + params);
            }
        };


        public abstract void doAction(String params);
    }
}
