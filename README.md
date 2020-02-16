# ScoreLib
ScoreLib is a solution for violation calculation-like  
Lite and Simple to use

# Permissions
`scorelib.CategoryName.bypass` Bypass the Analyzer from the Category

# Import
Maven:
```
    <repositories>
        <repository>
            <id>scorelib</id>
            <url>https://raw.githubusercontent.com/Server-CT/ScoreLib/master/repo</url>
        </repository>
    </repositories>
    <dependencies>
            <dependency>
                <groupId>org.serverct</groupId>
                <artifactId>ScoreLib</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
    </dependencies>
```
# Usage
```java
package org.serverct.vl;

import org.bukkit.event.EventHandler;
import org.serverct.vl.analyzer.Executor;
import org.serverct.vl.analyzer.Stat;
import org.serverct.vl.bean.Mode;
import org.serverct.vl.events.ConditionsMetEvent;
import org.serverct.vl.util.Category;

import java.util.UUID;

public class Example {
    Category Fishing=new Category("Fishing");
    public Example(){
        Executor a =Fishing.addExecutor("XD");
        /*
        Action:
        when something reach X,Executor call the events.(ConditionMetEvent)
         */
        a.putAction(6,true);// Executor will call event when something reach 6
        a.putAction(5,true);// Executor will call event when something reach 5
        /*
        Executor modes:
        TRIG: call events when score update (default)
        CHECKER: call events when ScoreUpdater check stats
        MIX: for all.
         */
        a.setMode(Mode.CHECKER);
        /*
        Executor has some functions for automatic: ScoreTrigger and ScoreUpdater.
        ScoreTrigger call event when something reach the score you set.
        ScoreUpdater check every stats' last modified time and lower increase timeout stat' score.
        Set AliveTime to -1 for disabled Updater.
         */
        a.start(20,1);//20 ticks for updater cycle,1 score for lower increase amount
        
        // Just Stat.
        Stat b=Fishing.addStat("CounterOfFishes");
        b.addScore(UUID.randomUUID(),1);
        b.fromScore(1);//return ids which score is 1
    }
    @EventHandler
    public void onScore(ConditionsMetEvent e){
        getLogger().info(e.getTaskID()+" : Object: "+e.getUUID().toString()+" Reached "+e.getScore());
        //6 or 5
    }
}

```