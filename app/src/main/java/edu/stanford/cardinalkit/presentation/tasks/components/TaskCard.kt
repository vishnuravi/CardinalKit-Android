package edu.stanford.cardinalkit.presentation.tasks.components

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.stanford.cardinalkit.common.Constants
import edu.stanford.cardinalkit.domain.models.tasks.CKTaskCategory
import edu.stanford.cardinalkit.domain.models.tasks.CKTaskLog
import edu.stanford.cardinalkit.presentation.surveys.SurveyActivity
import edu.stanford.cardinalkit.presentation.tasks.TasksViewModel
import edu.stanford.cardinalkit.ui.theme.PrimaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCard(
    id: String,
    title: String,
    description: String,
    category: CKTaskCategory,
    uri: String,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    fun launchSurvey(surveyName: String){
        val intent = Intent(context, SurveyActivity::class.java).apply {
            putExtra(Constants.SURVEY_NAME, surveyName)
        }
        context.startActivity(intent)
    }

    fun uploadTaskLog(id: String) {
        val taskLog = CKTaskLog(id)
        viewModel.uploadTaskLog(taskLog)
    }

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(8.dp).fillMaxWidth().clickable {
            when(category){
                CKTaskCategory.SURVEY -> {
                    launchSurvey(uri)
                    uploadTaskLog(id)
                }
            }
        },
        colors = CardDefaults.cardColors(Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Filled.Assignment,
                        tint= PrimaryTheme,
                        contentDescription = "Complete a survey",
                        modifier = Modifier.padding(15.dp)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(horizontal=7.dp),
                        fontSize = 20.sp
                    )
                    Text(
                        text = description,
                        modifier = Modifier.padding(horizontal=7.dp),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}