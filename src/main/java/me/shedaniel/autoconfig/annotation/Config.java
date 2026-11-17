//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Config {
    String name();

    public static class Gui {
        private Gui() {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target({ElementType.TYPE})
        public @interface CategoryBackgrounds {
            CategoryBackground[] value();
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target({ElementType.TYPE})
        @Repeatable(CategoryBackgrounds.class)
        public @interface CategoryBackground {
            String category();

            String background();
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target({ElementType.TYPE})
        public @interface Background {
            String TRANSPARENT = "cloth-config2:transparent";

            String value();
        }
    }
}
